package c01

object Mutiply {

  class MutiplyCounter extends Number1Context {

    def genNum1(n: Int): Unit = {
      for (_ <- 1 to n) {
        append(new Number1S)
      }
      append(new Number1T)
    }

    def genNum2(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(genNum2(n - 1, zero)) else zero

    def countNumber1: Int = {
      var i  = 0
      var n1 = num1
      while (n1.isInstanceOf[Number1S]) {
        i += 1
        n1 = n1.tail
      }
      i
    }

    def countNumber2(n: Number2): Int = {
      n match {
        case Number2S(tail) => countNumber2(tail) + 1
        case Number2T(_)    => 0
      }
    }

  }

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val context1: MutiplyCounter = new MutiplyCounter
        context1.genNum1(i1)
        lazy val n2: context1.Number2     = context1.genNum2(i2, n2Zero)
        lazy val n2Zero: context1.Number2 = context1.Number2T(() => n2)
        assert(context1.countNumber1 == i1)
        context1.num1.method1(n2)
        assert(context1.countNumber2(n2) == i2)
        assert(context1.countNumber1 == i1 * i2)
      }
    }
  }

}
