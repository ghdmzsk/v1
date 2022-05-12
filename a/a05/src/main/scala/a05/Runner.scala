package a05

import scala.util.Random
import zio._
import zio.logging._

object Runner {

  val loggingEnv: URLayer[ZEnv, Logging] =
    Logging.console(logLevel = LogLevel.Info, format = LogFormat.ColoredLogFormat()) >>> Logging.withRootLoggerName("乘法")

  def info(str: String): Unit = Runtime.default.unsafeRun(log.info(str).provideLayer(loggingEnv))

  def randomGen: Int = math.abs(Random.nextInt() % 10)

  object GenNumber1 {
    def gen1(n: Int, size: Int): Number1_1 = {
      if (n > 0 && size > 0) {
        val m    = randomGen
        val left = if (size - m - 1 > 0) m else 0
        Number1_1S(gen1(n - 1, size - left - 1), gen2(randomGen, left))
      } else if (size > 0) {
        val m    = randomGen
        val left = if (size - m - 1 > 0) m else 0
        Number1_1S(gen1(n - 1, size - left - 1), gen2(randomGen, left))
      } else
        Number1_1T
    }

    def gen2(n: Int, size: Int): Number1_2 = {
      if (n > 0 && size > 0) {
        val m    = randomGen
        val left = if (size - m > 0) m else 0
        Number1_2S(gen2(n - 1, left), gen1(randomGen, size - left))
      } else if (size > 0) {
        val m    = randomGen
        val left = if (size - m > 0) m else 0
        Number1_2S(gen2(n - 1, left), gen1(randomGen, size - left))
      } else
        Number1_2T
    }
  }

  def genNumber2_1(n: Int, tail2: => Number2_2, zero: => Number2_1): Number2_1 =
    Number2_1S(if (n > 0) genNumber2_1Impl(n - 1, zero) else zero, tail2)
  def genNumber2_1Impl(n: Int, zero: => Number2_1): Number2_1 = if (n > 0) Number2_1T(genNumber2_1Impl(n - 1, zero)) else zero

  def genNumber2_2(n: Int, zero: => Number2_2): Number2_2 = if (n > 0) Number2_2S(genNumber2_2(n - 1, zero)) else zero

  case class Num(i1: Int, i2: Int)

  object paramCount1 {
    def count1(num1_1: Number1_1): Num = num1_1 match {
      case Number1_1S(tail1, tail2) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        Num(i1 = n1.i1 + n2.i1 + 1, n1.i2 + n2.i2)
      case Number1_1T =>
        Num(i1 = 0, i2 = 0)
    }

    def count2(num1_2: Number1_2): Num = num1_2 match {
      case Number1_2S(tail2, tail1) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        Num(i1 = n1.i1 + n2.i1, n1.i2 + n2.i2 + 1)
      case Number1_2T =>
        Num(i1 = 0, i2 = 0)
    }
  }

  object paramCount2 {
    def count1(num2: Number2_1): Num = num2 match {
      case Number2_1S(tail1, tail2) =>
        val n1 = count1(tail1)
        val n2 = count2(tail2)
        Num(i1 = n1.i1 + n2.i1 + 1, i2 = n1.i2 + n2.i2)
      case Number2_1T(tail) =>
        val n1 = count1(tail)
        Num(i1 = n1.i1 + 1, i2 = n1.i2)
      case Number2_1U(_) =>
        Num(i1 = 0, i2 = 0)
    }

    def count2(num2: Number2_2): Num = num2 match {
      case Number2_2S(tail1) =>
        val n1 = count2(tail1)
        Num(i1 = n1.i1, i2 = n1.i2 + 1)
      case Number2_2T(_) =>
        Num(i1 = 0, i2 = 0)
    }
  }

  object resultCount {
    def count1(num1: Number3_1): Num = num1 match {
      case Number3_1S(tail) =>
        val n1 = count2(tail)
        Num(i1 = n1.i1, i2 = n1.i2 + 1)
      case Number3_1T(tail) =>
        val n1 = count1(tail)
        Num(i1 = n1.i1, i2 = n1.i2 + 1)
      case Number3_1U => Num(i1 = 0, i2 = 0)
    }

    def count2(num2: Number3_2): Num = num2 match {
      case Number3_2T(tail2, tail1) =>
        val n1 = count2(tail2)
        val n2 = count1(tail1)
        Num(i1 = n1.i1 + n2.i1 + 1, i2 = n1.i2 + n2.i2)
      case Number3_2U(tail) =>
        val n1 = count2(tail)
        Num(i1 = n1.i1 + 1, i2 = n1.i2)
      case Number3_2W => Num(i1 = 0, i2 = 0)
    }
  }

  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 1 to 20
      i2 <- 1 to 5
      i3 <- 1 to 10
      i4 <- 1 to 10
    } {
      val num1_1: Number1_1          = GenNumber1.gen1(i2, i1)
      val num1_2: Number1_2          = GenNumber1.gen2(i2, i1)
      lazy val num2_1: Number2_1     = genNumber2_1(i3, num2_2, num2_1Zero)
      lazy val num2_1Zero: Number2_1 = Number2_1U(() => num2_1)
      lazy val num2_2: Number2_2     = genNumber2_2(i4, num2_2Zero)
      lazy val num2_2Zero: Number2_2 = Number2_2T(() => num2_1)

      val numCount1 = num2_2Zero.method3(num1_1)
      val numCount2 = num1_1.method1(num2_1)
      val numCount5 = num1_2.method2(num2_1)
      val numCount6 = num1_1.method1(num2_1Zero)
      val numCount7 = num1_2.method2(num2_1Zero)
      val numCount8 = num2_2.method3(num1_1)

      val count1_1 = paramCount1.count1(num1_1)
      val count1_2 = paramCount1.count2(num1_2)

      val count2_1 = paramCount2.count1(num2_1)

      val count3 = resultCount.count1(numCount1)
      val count4 = resultCount.count1(numCount2)
      val count5 = resultCount.count2(numCount5)
      val count6 = resultCount.count1(numCount6)
      val count7 = resultCount.count2(numCount7)
      val count8 = resultCount.count1(numCount8)

      val count9 = count1_1.i1 * (count2_1.i1 - count2_1.i2)

      assert(count1_1.i1 == count1_2.i1)
      assert(count1_1.i1 == i1)
      assert(count2_1.i1 == i3)
      assert(count2_1.i2 == i4)

      info("//==")
      info(s"$count2_1")
      info(s"$count1_1 * ${count2_1.i1 - count2_1.i2} = ${count1_1.i1 * (count2_1.i1 - count2_1.i2)}")
      info(s"自然数乘法同构准确结果：$count3 $count4 $count5")
      info(s"自然数乘法同构其它结果：$count6 $count7 $count8")

      assert(count9 * 2 == count3.i1 + count5.i1 - count3.i2 - count5.i2)

      val assert1 = count9 == count3.i1 - count3.i2 + 1
      val assert2 = count9 == count5.i1 - count5.i2 - 1

      assert(assert1 && assert2)
    }

  }

}
