package a13

object Runner {

  lazy val numAny: Number1 = Number1S(() => Number2S(numN2, Number1S(() => Number2S(numN2, numN1), () => num3)), () => num3)
  lazy val num3: Number3   = Number3S(numN8, numN1)
  lazy val numN1: Number1  = Number1S(() => Number2S(numN2, numN3), () => numN8)
  lazy val numN2: Number2  = Number2S(numN9, numN3)
  lazy val numN3: Number1  = Number1S(() => Number2S(numN2, numN4), () => numN8)
  lazy val numN4: Number1  = Number1S(() => Number2S(numN2, numN5), () => numN8)
  lazy val numN5: Number1  = Number1S(() => Number2S(numN2, numN5), () => numN8)
  lazy val numN6: Number2  = Number2S(numN2, numN7)
  lazy val numN7: Number1 =
    Number1S(() => Number2T(() => numN2), () => Number3S(Number3S(Number3S(Number3S(numN8, Number1T), Number1T), Number1T), Number1T))
  lazy val numN8: Number3 = Number3T(() => num3)
  lazy val numN9: Number2 = Number2T(() => numN2)

  case class Count(num1: Int, num2: Int, num3: Int)

  def countNumber1(num1: Number1): Count = num1 match {
    case Number1S(tail2, tail3) =>
      val c1 = countNumber2(tail2())
      val c2 = countNumber3(tail3())
      Count(num1 = c1.num1 + c2.num1 + 1, num2 = c1.num2 + c2.num2, num3 = c1.num3 + c2.num3)
    case Number1T =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def countNumber2(num2: Number2): Count = num2 match {
    case Number2S(tail2, tail1) =>
      val c1 = countNumber2(tail2)
      val c2 = countNumber1(tail1)
      Count(num1 = c1.num1 + c2.num1, num2 = c1.num2 + c2.num2 + 1, num3 = c1.num3 + c2.num3)
    case Number2T(_) =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def countNumber3(num3: Number3): Count = num3 match {
    case Number3S(tail3, tail1) =>
      val c1 = countNumber3(tail3)
      val c2 = countNumber1(tail1)
      Count(num1 = c1.num1 + c2.num1, num2 = c1.num2 + c2.num2, num3 = c1.num3 + c2.num3 + 1)
    case Number3T(_) =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def main(arr: Array[String]): Unit = {
    val n = countNumber1(numAny)
    println(n)
  }

}
