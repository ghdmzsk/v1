package a08.akka_test

import akka.actor._
import akka.actor.typed.Behavior
import akka.event.{Logging, LoggingAdapter}
import akka.pattern.Patterns
import akka.util.Timeout

import scala.concurrent.duration._
import typed.scaladsl.AskPattern._

import java.util.concurrent.Callable
import scala.concurrent.{Await, Future}

object AkkaTest extends App {

// ========== output ==========

//发送执行命令：Plus(2)
//发送执行命令：Minus(4)
//发送执行命令：Plus(6)
//发送执行命令：Minus(7)
//发送执行命令：Plus(34)
//+2
//-4
//+6
//-7
//+34
//计算结果：31

  implicit val system                                       = ActorSystem.create("akka-test-system")
  implicit val typedActorSystem: typed.ActorSystem[Nothing] = typed.ActorSystem.wrap(system)

  import typedActorSystem.executionContext
  implicit val timeout = Timeout(3.seconds)

  val receiveActor = typedActorSystem.systemActorOf(TypedActorTest.init(0), "receive-plus-minus")

  val classicActor = system.actorOf(Props(classOf[ClassicActor]))

  classicActor ! ClassicActor.Value(2)(receiveActor)
  classicActor ! ClassicActor.Value(4)(receiveActor)
  classicActor ! ClassicActor.Value(6)(receiveActor)
  classicActor ! ClassicActor.Value(7)(receiveActor)
  classicActor ! ClassicActor.Value(34)(receiveActor)

  private val delayCall: Callable[Future[Any]] = () => Future.successful(())

  private def delayMillions[T](million: Long): Future[Any] = Patterns.after(
    million.milliseconds,
    system.scheduler,
    typedActorSystem.executionContext,
    delayCall
  )

  val n1 = for {
    _    <- delayMillions(2000)
    data <- receiveActor ? ((s: typed.ActorRef[Int]) => TypedActorTest.ReplyTo(s))
  } yield typedActorSystem.log.info(s"计算结果：$data")

  n1.onComplete(_ => system.terminate())
  Await.result(n1, Duration.Inf)

}

object ClassicActor {
  sealed trait PlusMinus
  case class Value(value: Int)(val sendTo: typed.ActorRef[TypedActorTest.PlusMinus]) extends PlusMinus
}

class ClassicActor extends Actor {
  implicit val ec = context.system.dispatcher

  val log: LoggingAdapter = Logging.getLogger(context.system, this)

  var needPlus: Boolean = true

  import ClassicActor._
  override def receive: Receive = { case model @ Value(v) =>
    val data = if (needPlus) TypedActorTest.Plus(v) else TypedActorTest.Minus(v)
    needPlus = !needPlus
    log.info(s"发送执行命令：$data")
    model.sendTo ! data
  }
}

object TypedActorTest {
  trait PlusMinus
  case class Plus(value: Int)                       extends PlusMinus
  case class Minus(value: Int)                      extends PlusMinus
  case class ReplyTo(actorRef: typed.ActorRef[Int]) extends PlusMinus

  def init(count: Int): typed.Behavior[PlusMinus] = typed.scaladsl.Behaviors.setup(s => new TypedActorTest(s, count))
}

import TypedActorTest._
class TypedActorTest(context: typed.scaladsl.ActorContext[PlusMinus], count: Int)
    extends typed.scaladsl.AbstractBehavior[PlusMinus](context) {

  override def onMessage(msg: PlusMinus): Behavior[PlusMinus] = msg match {
    case Plus(value) =>
      context.log.info(s"执行运算 +$value")
      init(count + value)
    case Minus(value) =>
      context.log.info(s"执行运算 -$value")
      init(count - value)
    case ReplyTo(actorRef) =>
      actorRef ! count
      typed.scaladsl.Behaviors.stopped
  }

}
