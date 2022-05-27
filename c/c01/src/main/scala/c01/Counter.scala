package c01

import scala.annotation.tailrec

trait Number1 {
  var tail: Number1
}

class Number1Context(var num1: Number1, var cursor: Int, var zero: Number1) {

  private def getPreCurrent: Number1 = {
    if(num1 == null){
      null
    } else {
      @tailrec
      def getCurrentImpl(i: Int, n: Number1): Number1 = if (i > 0) getCurrentImpl(i - 1, n.tail) else n
      getCurrentImpl(cursor - 1, num1)
    }
  }

  private def getCurrent: Number1 = {
    if(num1 == null) {
      null
    } else {
      @tailrec
      def getCurrentImpl(i: Int, n: Number1): Number1 = if (i > 0) getCurrentImpl(i - 1, n.tail) else n
      getCurrentImpl(cursor, num1)
    }
  }

  private def dropCurrent: Unit = {
    val preCurrent = getPreCurrent
    if(preCurrent.tail == null) {
      zero = null
    }
    if (preCurrent == num1) {
      num1 = num1.tail
    } else {
      preCurrent.tail = preCurrent.tail.tail
    }
  }

  private def append(number1: Number1): Unit = {
    if (zero == null) {
      num1 = number1
      zero = number1
    } else {
      zero.tail = number1
      zero = number1
    }
  }

  private def ignore: Unit = {
    if(zero != null) {
      cursor += 1
    }
  }

  class Number1S(override var tail: Number1) extends Number1 {
    def method1(num2: Number2): Unit = {

    }
  }

}

trait Number2 {
  def method2(num1: Number1): Unit
}

case class Number2S(tail: Number2) extends Number2 {
  def method2(num1: Number1, context: Number1Context): Unit
}
case class Number2T(tail: () => Number2) extends Number2 {
  def method2(num1: Number1, context: Number1Context): Unit
}
