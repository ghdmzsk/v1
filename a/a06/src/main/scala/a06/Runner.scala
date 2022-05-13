package a06

import scala.util.Random

object Runner {

  def randomGen: Int = math.abs(Random.nextInt() % 10)

  object GenNumber1 {
    def gen1(n1: Int, n2: Int, deep: Int): Number1_1 = {
      if (deep > 0 && n1 > 0) {
        val m1    = randomGen
        val m2    = randomGen
        val left1 = if (n1 - 1 - m1 > 0) n1 - 1 - m1 else 0
        val left2 = if (n2 - m1 > 0) n2 - m1 else 0
        Number1_1S(gen1(n1 - left1 - 1 + m2, n2 - left2 + m2, deep - 1), gen2(left1 + m2, left2 + m2, deep - 1))
      } else {
        if (n1 > 0) {
          Number1_1S(gen1(n1 - 1, n2, deep), Number1_2T)
        } else if (n2 > 0) {
          Number1_1S(Number1_1T, Number1_2S(gen2(n1, n2, deep), Number1_1T))
        } else {
          Number1_1T
        }
      }
    }

    def gen2(n1: Int, n2: Int, deep: Int): Number1_2 = {
      if (deep > 0 && n2 > 0) {
        val m1    = randomGen
        val m2    = randomGen
        val left1 = if (n1 - m1 > 0) n1 - m1 else 0
        val left2 = if (n2 - 1 - m1 > 0) n2 - 1 - m1 else 0
        Number1_2S(gen2(n1 - left1 + m2, n2 - left2 - 1 + m2, deep - 1), gen1(left1 + m2, left2 + m2, deep - 1))
      } else {
        if (n2 > 0) {
          Number1_2S(gen2(n1, n2 - 1, deep), Number1_1T)
        } else if (n1 > 0) {
          Number1_2S(Number1_2T, Number1_1S(gen1(n1, n2, deep), Number1_2T))
        } else {
          Number1_2T
        }
      }
    }
  }

  object GenNumber2 {
    def gen1(n1: Int, n2: Int, deep: Int, tail1: => Number2_1, tail2: => Number2_2): Number2_1 = {
      if (deep > 0 && n1 > 0) {
        val m1    = randomGen
        val m2    = randomGen
        val left1 = if (n1 - 1 - m1 > 0) n1 - 1 - m1 else 0
        val left2 = if (n2 - m1 > 0) n2 - m1 else 0
        Number2_1S(gen1(n1 - left1 - 1 + m2, n2 - left2 + m2, deep - 1, tail1, tail2), gen2(left1 + m2, left2 + m2, deep - 1, tail1, tail2))
      } else {
        if (n1 > 0) {
          Number2_1S(gen1(n1 - 1, n2, deep, tail1, tail2), tail2)
        } else if (n2 > 0) {
          Number2_1S(tail1, Number2_2S(gen2(n1, n2, deep, tail1, tail2), tail1))
        } else {
          tail1
        }
      }
    }

    def gen2(n1: Int, n2: Int, deep: Int, tail1: => Number2_1, tail2: => Number2_2): Number2_2 = {
      if (deep > 0 && n2 > 0) {
        val m1    = randomGen
        val m2    = randomGen
        val left1 = if (n1 - m1 > 0) n1 - m1 else 0
        val left2 = if (n2 - 1 - m1 > 0) n2 - 1 - m1 else 0
        Number2_2S(gen2(n1 - left1 + m2, n2 - left2 - 1 + m2, deep - 1, tail1, tail2), gen1(left1 + m2, left2 + m2, deep - 1, tail1, tail2))
      } else {
        if (n2 > 0) {
          Number2_2S(gen2(n1, n2 - 1, deep, tail1, tail2), tail1)
        } else if (n1 > 0) {
          Number2_2S(tail2, Number2_1S(gen1(n1, n2, deep, tail1, tail2), tail2))
        } else {
          tail2
        }
      }
    }
  }

  case class Num(i1: Int, i2: Int) {
    def plus(other: Num): Num = Num(i1 = i1 + other.i1, i2 = i2 + other.i2)
    def upI1: Num             = Num(i1 = i1 + 1, i2 = i2)
    def upI2: Num             = Num(i1 = i1, i2 = i2 + 1)
    def value: Int            = i1 - i2
  }

  object paramCount1 {
    def count1(num1_1: Number1_1): Num = num1_1 match {
      case Number1_1S(tail1, tail2) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        n1.plus(n2).upI1
      case Number1_1T =>
        Num(i1 = 0, i2 = 0)
    }

    def count2(num1_2: Number1_2): Num = num1_2 match {
      case Number1_2S(tail2, tail1) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        n1.plus(n2).upI2
      case Number1_2T =>
        Num(i1 = 0, i2 = 0)
    }
  }

  object paramCount2 {
    def count1(num2: Number2_1): Num = num2 match {
      case Number2_1S(tail1, tail2) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        n1.plus(n2).upI1
      case Number2_1T(_, _) =>
        Num(i1 = 0, i2 = 0)
    }

    def count2(num2: Number2_2): Num = num2 match {
      case Number2_2S(tail2, tail1) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        n1.plus(n2).upI2
      case Number2_2T(_, _) =>
        Num(i1 = 0, i2 = 0)
    }
  }

  object resultCount {
    def count1(num1: Number3_1): Num = num1 match {
      case Number3_1S(tail) =>
        val n1 = count1(tail)
        n1.upI1
      case Number3_1T(tail1, tail2) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        n1.plus(n2).upI1
      case Number3_1U =>
        Num(i1 = 0, i2 = 0)
    }

    def count2(num2: Number3_2): Num = num2 match {
      case Number3_2S(tail) =>
        val n1 = count2(tail)
        n1.upI2
      case Number3_2T(tail2, tail1) =>
        val n1 = count2(tail2)
        val n2 = count1(tail1)
        n1.plus(n2).upI2
      case Number3_2U =>
        Num(i1 = 0, i2 = 0)
    }
  }

  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 1 to 10
      i2 <- 1 to 10
      i3 <- 1 to 10
      i4 <- 1 to 10
      i5 <- 1 to 5
    } {
      val num1_1: Number1_1 = GenNumber1.gen1(i1, i2, i5)
      val num1_2: Number1_2 = GenNumber1.gen2(i1, i2, i5)

      lazy val num2_1Zero: Number2_1 = Number2_1T(() => num2_1Zero, () => num2_2Zero)
      lazy val num2_2Zero: Number2_2 = Number2_2T(() => num2_2Zero, () => num2_1Zero)

      val num2_1: Number2_1 = GenNumber2.gen1(i3, i4, i5, num2_1Zero, num2_2Zero)
      val num2_2: Number2_2 = GenNumber2.gen2(i3, i4, i5, num2_1Zero, num2_2Zero)

      val count1 = paramCount1.count1(num1_1)
      val count2 = paramCount2.count1(num2_1)

      val count3 = resultCount.count1(num1_1.method1(num2_1))

      val count4 = paramCount1.count2(num1_2)
      val count5 = paramCount2.count2(num2_2)

      val count6 = resultCount.count2(num2_2.method3(num1_2))

      println("// ====================")
      println(count1.value, count2.value, count1.plus(count2).value, count3.value)
      println(count4.value, count5.value, count4.plus(count5).value, count6.value)

      /*if (count1.plus(count2).value != count3.value) {
        println(count1.value, count2.value, count1.plus(count2).value, count3.value)
      } else {
        println(true)
      }*/

      /*if (count4.plus(count5).value != count6.value) {
        println(count4.value, count5.value, count4.plus(count5).value, count6.value)
      } else {
        println(true)
      }*/

    }

  }

}
