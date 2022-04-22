package a13

import scala.util.Random

object Runner {

  def randomGen: Int = math.abs(Random.nextInt() % 10)

  def genNumber1(n: Int, deep: Int, zero1: => Number1, zero2: => Number2, zero3: => Number3): Number1 = {
    if (deep > 0)
      Number1S(genNumber3(randomGen, deep - 1, zero1, zero2, zero3), genNumber1(n - 1, deep - 1, zero1, zero2, zero3))
    else
      Number1T(() => zero1)
  }
  def genNumber2(n: Int, deep: Int, zero1: => Number1, zero2: => Number2, zero3: => Number3): Number2 = {
    if (n > 0)
      Number2S(genNumber1(randomGen, deep, zero1, zero2, zero3), genNumber2(n - 1, deep - 1, zero1, zero2, zero3))
    else
      Number2T(() => zero2)
  }
  def genNumber3(n: Int, deep: Int, zero1: => Number1, zero2: => Number2, zero3: => Number3): Number3 = {
    if (n > 0)
      Number3S(genNumber2(randomGen, deep, zero1, zero2, zero3), genNumber3(n - 1, deep - 1, zero1, zero2, zero3))
    else
      Number3T(() => zero3)
  }

  def genNumber1_2(n: Int, deep: Int): Number1 = {
    if (deep > 0)
      Number1S(genNumber3_2(randomGen, deep - 1), genNumber1_2(n - 1, deep - 1))
    else
      Number1U
  }
  def genNumber2_2(n: Int, deep: Int): Number2 = {
    if (n > 0)
      Number2S(genNumber1_2(randomGen, deep), genNumber2_2(n - 1, deep - 1))
    else
      Number2U
  }
  def genNumber3_2(n: Int, deep: Int): Number3 = {
    if (n > 0)
      Number3S(genNumber2_2(n - 1, deep), genNumber3_2(n - 1, deep - 1))
    else
      Number3U
  }

  case class Count(num1: Double, num2: Double, num3: Double)

  def countNumber1(num1: Number1): Count = num1 match {
    case Number1S(tail2, tail3) =>
      val c1 = countNumber3(tail2)
      val c2 = countNumber1(tail3)
      Count(num1 = c1.num1 + c2.num1 + 1, num2 = c1.num2 + c2.num2, num3 = c1.num3 + c2.num3)
    case Number1T(_) =>
      Count(num1 = 0, num2 = 0, num3 = 0)
    case Number1U =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def countNumber2(num2: Number2): Count = num2 match {
    case Number2S(tail2, tail1) =>
      val c1 = countNumber1(tail2)
      val c2 = countNumber2(tail1)
      Count(num1 = c1.num1 + c2.num1, num2 = c1.num2 + c2.num2 + 1, num3 = c1.num3 + c2.num3)
    case Number2T(_) =>
      Count(num1 = 0, num2 = 0, num3 = 0)
    case Number2U =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def countNumber3(num3: Number3): Count = num3 match {
    case Number3S(tail3, tail1) =>
      val c1 = countNumber2(tail3)
      val c2 = countNumber3(tail1)
      Count(num1 = c1.num1 + c2.num1, num2 = c1.num2 + c2.num2, num3 = c1.num3 + c2.num3 + 1)
    case Number3T(_) =>
      Count(num1 = 0, num2 = 0, num3 = 0)
    case Number3U =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def main(arr: Array[String]): Unit = {
    lazy val num1: Number1 = genNumber1(10, 3, num1, num2, num3)
    lazy val num2: Number2 = genNumber2(10, 3, num1, num2, num3)
    lazy val num3: Number3 = genNumber3(10, 3, num1, num2, num3)

    val num2_1: Number2 = genNumber2_2(10, 3)

    println(countNumber2(num2))
    println(countNumber3(num3))

    println(countNumber2(num2_1))

    println(countNumber2(num1.method1(num2_1)))

    val a1   = countNumber2(num2).num1 / (countNumber2(num2).num2 - countNumber2(num2).num3)
    val a1_2 = countNumber1(num1).num1 / (countNumber1(num1).num2 - countNumber1(num1).num3)
    val a2   = countNumber3(num3).num1 / (countNumber3(num3).num2 - countNumber3(num3).num3)
    println("Be x num 1 1", a1)
    println("Be x num 2 1", a2)

    val a3   = countNumber2(num2).num2 / (countNumber2(num2).num1 - countNumber2(num2).num3)
    val a3_2 = countNumber1(num1).num2 / (countNumber1(num1).num1 - countNumber1(num1).num3)
    val a4   = countNumber3(num3).num2 / (countNumber3(num3).num1 - countNumber3(num3).num3)
    println("Be x num 1 2", a3)
    println("Be x num 2 2", a4)

    val a5   = countNumber2(num2).num3 / (countNumber2(num2).num1 + countNumber2(num2).num2)
    val a5_2 = countNumber1(num1).num3 / (countNumber1(num1).num1 - countNumber1(num1).num2)
    val a6   = countNumber3(num3).num3 / (countNumber3(num3).num1 + countNumber3(num3).num2)
    println("Be x num 1 3", a5)
    println("Be x num 2 3", a6)

    val a7_2 = countNumber2(num2_1).num1 / (countNumber2(num2_1).num2 - countNumber2(num2_1).num3)
    println("x num 1 1", a7_2)

    val a9_2 = countNumber2(num2_1).num2 / (countNumber2(num2_1).num1 - countNumber2(num2_1).num3)
    println("x num 1 2", a9_2)

    val a11_2 = countNumber2(num2_1).num3 / (countNumber2(num2_1).num1 - countNumber2(num2_1).num2)
    println("x num 1 3", a11_2)

    val b1 = List(a1, a2, a3, a4, a5, a6, a1_2, a3_2, a5_2)
    val b2 = List(a7_2, a9_2, a11_2)

    val c1 = countNumber2(num1.method1(num2_1)).num1 / (countNumber2(num1.method1(num2_1)).num2 - countNumber2(num1.method1(num2_1)).num3)
    println("s num 1 1", c1)
    val c2 = countNumber2(num1.method1(num2_1)).num2 / (countNumber2(num1.method1(num2_1)).num1 - countNumber2(num1.method1(num2_1)).num3)
    println("s num 1 2", c2)
    val c3 = countNumber2(num1.method1(num2_1)).num3 / (countNumber2(num1.method1(num2_1)).num1 - countNumber2(num1.method1(num2_1)).num2)
    println("s num 1 3", c3)

    val c4 = countNumber1(num2_1.method2(num3)).num1 / (countNumber1(num2_1.method2(num3)).num2 - countNumber1(num2_1.method2(num3)).num3)
    println("s num 1 1", c4)
    val c5 = countNumber1(num2_1.method2(num3)).num2 / (countNumber1(num2_1.method2(num3)).num1 - countNumber1(num2_1.method2(num3)).num3)
    println("s num 1 2", c5)
    val c6 = countNumber1(num2_1.method2(num3)).num3 / (countNumber1(num2_1.method2(num3)).num1 - countNumber1(num2_1.method2(num3)).num2)
    println("s num 1 3", c6)

    val d1 = List(c1, c2, c3, c4, c5, c6)
    for {
      i1 <- b1
      i2 <- b2
    } yield {
      println(i1 * i2)
      d1.filter(t => math.abs(i1 * i2 - t) < 0.01).foreach(s => println("match", i1 * i2, s))
    }
  }

}
