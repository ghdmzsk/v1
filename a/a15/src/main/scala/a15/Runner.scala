package a15

import scala.util.Random

object Runner {

  def randomGen: Int = math.abs(Random.nextInt() % 10)

  def genNumber1_1(n: Int, deep: Int): Number1_1 = {
    if (deep > 0 && n > 0)
      Number1_1S(genNumber1_2(randomGen, deep - 1))
    else
      Number1_1T
  }

  def genNumber1_2(n: Int, deep: Int): Number1_2 = {
    if (deep > 0 && n > 0)
      Number1_2S(genNumber1_2(randomGen, deep - 1), genNumber1_1(n - 1, deep))
    else
      Number1_2T
  }

  def genNumber2(n: Int, zero: => Number2): Number2 = if (n > 0) Number2S(genNumber2(n - 1, zero)) else zero

  def countNumber1_1(num1_1: Number1_1): Int = num1_1 match {
    case Number1_1S(tail) => countNumber1_2(tail) + 1
    case Number1_1T       => 0
  }

  def countNumber1_2(num1_2: Number1_2): Int = num1_2 match {
    case Number1_2S(tail2, tail1) => countNumber1_1(tail1) + countNumber1_2(tail2)
    case Number1_2T               => 0
  }

  def countNumber2(num2: Number2): Int = num2 match {
    case Number2S(tail) => countNumber2(tail) + 1
    case Number2T(_)    => 0
  }

  def countNumber3_1(num1: Number3_1): Int = num1 match {
    case Number3_1S(tail) => countNumber3_2(tail) + 1
  }

  def countNumber3_2(num2: Number3_2): Int = num2 match {
    case Number3_2S(tail2_1, tail12_2) => countNumber3_2(tail2_1) + countNumber3_2(tail12_2)
    case Number3_2T(tail)              => countNumber3_1(tail)
    case Number3_2U                    => 0
  }

  def main(arr: Array[String]): Unit = {

    for {
      i1 <- 1 to 20
      i2 <- 1 to 5
      i3 <- 1 to 10
    } {
      val num1: Number1_2        = genNumber1_2(i1, i2)
      lazy val num2: Number2     = genNumber2(i3, num2Zero)
      lazy val num2Zero: Number2 = Number2T(() => num2)
      val numCount               = num1.method2(num2)
      val count1                 = countNumber1_2(num1)
      val count2                 = countNumber2(num2)
      val count3                 = countNumber3_2(numCount)
      println(count1, count2, count3, count1 * count2)
      assert(count1 * count2 == count3)
    }

  }

}
