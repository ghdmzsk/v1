package c01

import scala.annotation.tailrec

class Number1Context {

  var num1: Number1 = null
  var cursor: Int   = 0
  var zero: Number1 = null

  private def getPreCurrent: Number1 = {
    if (num1 == null) {
      null
    } else {
      @tailrec
      def getCurrentImpl(i: Int, n: Number1): Number1 = if (i > 0) getCurrentImpl(i - 1, n.tail) else n
      getCurrentImpl(cursor - 1, num1)
    }
  }

  private def getCurrent: Number1 = {
    if (num1 == null) {
      null
    } else {
      @tailrec
      def getCurrentImpl(i: Int, n: Number1): Number1 = if (i > 0) getCurrentImpl(i - 1, n.tail) else n
      getCurrentImpl(cursor, num1)
    }
  }

  private def dropCurrent: Unit = {
    val preCurrent = getPreCurrent
    if (zero == null) {
      return
    }
    if (preCurrent.tail == null) {
      zero = null
    }
    if (preCurrent == num1) {
      num1 = num1.tail
    } else {
      preCurrent.tail = preCurrent.tail.tail
    }
  }

  protected def append(number1: Number1): Unit = {
    if (zero == null) {
      num1 = number1
      zero = number1
    } else {
      zero.tail = number1
      zero = number1
    }
  }

  private def ignore: Unit = {
    if (zero != null) {
      cursor += 1
    }
  }

  trait Number1 {
    var tail: Number1 = null
    def method1(num2: Number2): Unit
  }

  class Number1S extends Number1 {
    override def method1(num2: Number2): Unit = {
      dropCurrent
      num2.method2
    }
  }

  class Number1T extends Number1 {
    override def method1(num2: Number2): Unit = {
      dropCurrent
      append(new Number1T)
    }
  }

  trait Number2 {
    def method2: Unit
  }
  case class Number2S(tail: Number2) extends Number2 {
    def method2: Unit = {
      append(new Number1S)
      tail.method2
    }
  }
  case class Number2T(tail: () => Number2) extends Number2 {
    def method2: Unit = {
      num1.method1(tail())
    }
  }

}
