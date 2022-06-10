package a08.akka_test

import akka.actor._
import akka.actor.typed.Behavior
import akka.pattern.Patterns
import akka.util.Timeout

import scala.concurrent.duration._
import typed.scaladsl.AskPattern._

import java.util.concurrent.Callable
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.Random

object AkkaTest extends App {

  implicit val system                                       = ActorSystem.create("akka-test-system")
  implicit val typedActorSystem: typed.ActorSystem[Nothing] = typed.ActorSystem.wrap(system)

  import typedActorSystem.executionContext
  implicit val timeout = Timeout(3.seconds)

  val receiveActor = typedActorSystem.systemActorOf(TypedActorTest.init(), "receive-plus-minus")

  val classicActor = system.actorOf(Props[ClassicActor])

  classicActor ! ClassicActor.Value(2)
  classicActor ! ClassicActor.Value(4)
  classicActor ! ClassicActor.Value(6)
  classicActor ! ClassicActor.Value(7)
  classicActor ! ClassicActor.Value(34)

  private def delayCall: Callable[Future[Any]] = {
    val f = Future.successful(())
    () => f
  }

  private def delayMillions[T](million: Long): Future[Any] = Patterns.after(
    Duration(million, MILLISECONDS),
    system.scheduler,
    implicitly[ExecutionContext],
    delayCall
  )

  val n1 = for {
    _    <- delayMillions(5000)
    data <- receiveActor ? ((s: typed.ActorRef[Int]) => TypedActorTest.ReplyTo(s))
  } yield println(data)

  n1.onComplete(_ => system.terminate())
  Await.result(n1, Duration.Inf)

}

object ClassicActor {
  sealed trait PlusMinus
  case class Value(value: Int) extends PlusMinus
}

class ClassicActor extends Actor {
  implicit val ec = context.system.dispatcher

  import ClassicActor._
  override def receive: Receive = { case Value(v) =>
    val data      = if (Random.nextBoolean()) TypedActorTest.Plus(v) else TypedActorTest.Minus(v)
    val findActor = context.actorSelection("akka://akka-test-system/system/receive-plus-minus").resolveOne(3.seconds)
    for (ac <- findActor) ac ! data
  }
}

object TypedActorTest {
  trait PlusMinus
  case class Plus(value: Int)                       extends PlusMinus
  case class Minus(value: Int)                      extends PlusMinus
  case class ReplyTo(actorRef: typed.ActorRef[Int]) extends PlusMinus

  def init(): typed.Behavior[PlusMinus] = typed.scaladsl.Behaviors.setup(s => new TypedActorTest(s))
}

import TypedActorTest._
class TypedActorTest(context: typed.scaladsl.ActorContext[PlusMinus]) extends typed.scaladsl.AbstractBehavior[PlusMinus](context) {
  var count: Int = 0

  override def onMessage(msg: PlusMinus): Behavior[PlusMinus] = msg match {
    case Plus(value) =>
      count += value
      typed.scaladsl.Behaviors.same
    case Minus(value) =>
      count -= value
      typed.scaladsl.Behaviors.same
    case ReplyTo(actorRef) =>
      actorRef ! count
      typed.scaladsl.Behaviors.stopped
  }
}
