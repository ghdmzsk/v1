package a15

import scala.util.Random
import zio._
import zio.logging._

object Runner {

  val loggingEnv: URLayer[ZEnv, Logging] =
    Logging.console(logLevel = LogLevel.Info, format = LogFormat.ColoredLogFormat()) >>> Logging.withRootLoggerName("乘法")

  def info(str: String): Unit = Runtime.default.unsafeRun(log.info(str).provideLayer(loggingEnv))

  def randomGen: Int = math.abs(Random.nextInt() % 10)

  def genNumber1_1(n: Int, deep: Int): Number1_1 = {
    if (deep > 0 && n > 0)
      Number1_1S(genNumber1_1(randomGen, deep - 1), genNumber1_2(randomGen, deep - 1))
    else
      Number1_1T
  }

  def genNumber1_2(n: Int, deep: Int): Number1_2 = {
    if (deep > 0 && n > 0)
      Number1_2S(genNumber1_2(randomGen, deep - 1), genNumber1_1(n - 1, deep))
    else
      Number1_2T
  }

  def genNumber2_1(n: Int, tail2: => Number2_2, zero: => Number2_1): Number2_1 =
    Number2_1S(if (n > 0) genNumber2_1Impl(n - 1, zero) else zero, tail2)
  def genNumber2_1Impl(n: Int, zero: => Number2_1): Number2_1 = if (n > 0) Number2_1T(genNumber2_1Impl(n - 1, zero)) else zero

  def genNumber2_2(n: Int, zero: => Number2_2): Number2_2 = if (n > 0) Number2_2S(genNumber2_2(n - 1, zero)) else zero

  def countNumber1_1(num1_1: Number1_1): Int = num1_1 match {
    case Number1_1S(tail1, tail2) => countNumber1_1(tail1) + countNumber1_2(tail2) + 1
    case Number1_1T               => 0
  }

  def countNumber1_2(num1_2: Number1_2): Int = num1_2 match {
    case Number1_2S(tail2, tail1) => countNumber1_1(tail1) + countNumber1_2(tail2)
    case Number1_2T               => 0
  }

  def countNumber2_1(num2: Number2_1): Int = num2 match {
    case Number2_1S(tail1, tail2) => countNumber2_1(tail1) + countNumber2_2(tail2) + 1
    case Number2_1T(tail)         => countNumber2_1(tail) + 1
    case Number2_1U(_)            => 0
  }

  def countNumber2_2(num2: Number2_2): Int = num2 match {
    case Number2_2S(tail1) => countNumber2_2(tail1) - 1
    case Number2_2T(_)     => 0
  }

  def countNumber3_1(num1: Number3_1): Int = num1 match {
    case Number3_1S(tail) => countNumber3_2(tail) - 1
    case Number3_1T(tail) => countNumber3_1(tail) - 1
    case Number3_1U       => 0
  }

  def countNumber3_2(num2: Number3_2): Int = num2 match {
    case Number3_2T(tail2, tail1) => countNumber3_2(tail2) + countNumber3_1(tail1) + 1
    case Number3_2U(tail)         => countNumber3_2(tail) + 1
    case Number3_2W               => 0
  }

  def main(arr: Array[String]): Unit = {

    var i = 0

    for {
      i1 <- 1 to 20
      i2 <- 1 to 5
      i3 <- 1 to 10
      i4 <- 1 to 10
    } {
      val num1: Number1_1            = genNumber1_1(i1, i2)
      lazy val num2_1: Number2_1     = genNumber2_1(i3, num2_2, num2_1Zero)
      lazy val num2_1Zero: Number2_1 = Number2_1U(() => num2_1)
      lazy val num2_2: Number2_2     = genNumber2_2(i4, num2_2Zero)
      lazy val num2_2Zero: Number2_2 = Number2_2T(() => num2_1)

      val numCount1 = num2_2Zero.method3(num1)
      val numCount2 = num1.method1(num2_1)
      val count1    = countNumber1_1(num1)
      val count2_1  = countNumber2_1(num2_1)
      val count3    = countNumber3_1(numCount1)
      val count4    = countNumber3_1(numCount2)
      if (i < 500) {
        info(s"$count1 * $count2_1 = $count3, $count4, ${count1 * count2_1}")
        info(s"抽象一，情况一与结果预期差：${count3 - count1 * count2_1}") // -1
        info(s"抽象一，情况二与结果预期差：${count4 - count1 * count2_1}") // -1
      }
      assert(count3 - count1 * count2_1 == -1)
      assert(count4 - count1 * count2_1 == -1)

      val num2: Number1_2 = genNumber1_2(i1, i2)
      val numCount5       = num2.method2(num2_1)
      val count7          = countNumber1_2(num2)
      val count8          = countNumber3_2(numCount5)
      if (i < 500) {
        info(s"$count7 * $count2_1 = $count8, ${count7 * count2_1}")
        info(s"抽象二，情况一与结果预期差：${count8 - count7 * count2_1}") // 1
      }
      assert(count8 - count7 * count2_1 == 1)
      i += 1
    }

  }

}
