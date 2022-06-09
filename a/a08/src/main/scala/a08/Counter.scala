package a08

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Number1 {
  def method1(number2: Number2, input: Input): Future[Unit]
}
case class Number1S(tail: () => Future[Number1]) extends Number1 {
  override def method1(number2: Number2, input: Input): Future[Unit] = tail().flatMap(t => number2.method2(t, input))
}
case class Number1T(tail: () => Future[Number1]) extends Number1 {
  override def method1(number2: Number2, input: Input): Future[Unit] = {
    input.push
    tail().flatMap(t => t.method1(number2, input))
  }
}
case class Number1U(tail: () => Future[Number1]) extends Number1 {
  override def method1(number2: Number2, input: Input): Future[Unit] = {
    input.push
    tail().flatMap(t => number2.method2(t, input))
  }
}

trait Number2 {
  def method2(number1: Number1, input: Input): Future[Unit]
}
case class Number2S(tail: () => Future[Number2]) extends Number2 {
  override def method2(number1: Number1, input: Input): Future[Unit] = tail().flatMap(t => number1.method1(t, input))
}
case class Number2T(tail: () => Future[Number2]) extends Number2 {
  override def method2(number1: Number1, input: Input): Future[Unit] = tail().flatMap(t => t.method2(number1, input))
}

case class Input(num1: Int, num2: Int, except: Int, countType: String)(numberCount: NumberCount) {
  def push: Unit = numberCount.receive(this)
}

class NumberCount {

  var map: Map[Input, Int] = Map.empty

  def receive(input: Input): Unit = this.synchronized {
    val value = map.get(input).getOrElse(0)
    map += ((input, value + 1))
  }

}
