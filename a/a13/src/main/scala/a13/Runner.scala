package a13

object Runner {

  lazy val numAny: Number1 = Number1S(() => Number2S(Number1S(() => Number2S(numN1, Number3T), () => Number3T), Number3T), () => Number3T)
  lazy val numN1: Number1  = Number1S(() => Number2S(numN7, Number3S(numN4, Number2T)), () => Number3T)
  lazy val numN2: Number2  = Number2S(Number1S(() => Number2S(numN5, Number3T), () => Number3T), Number3T)
  lazy val numN3: Number1  = Number1S(() => Number2S(Number1S(() => Number2T, () => Number3T), Number3T), () => Number3T)
  lazy val numN4: Number1  = Number1S(() => Number2S(numN7, Number3S(Number1T, numN6)), () => Number3T)
  lazy val numN5: Number1  = Number1S(() => Number2S(numN7, Number3T), () => Number3T)
  lazy val numN6: Number2  = Number2S(numN7, Number3T)
  lazy val numN7: Number1 =
    Number1S(() => Number2T, () => Number3S(Number1T, Number2S(Number1S(() => Number2S(Number1T, Number3T), () => Number3T), Number3T)))

  case class Count(num1: Int, num2: Int, num3: Int)

  def countNumber1(num1: Number1): Count = num1 match {
    case Number1S(num2, num3) =>
      val c1 = countNumber2(num2())
      val c2 = countNumber3(num3())
      Count(num1 = c1.num1 + c2.num1 + 1, num2 = c1.num2 + c2.num2, num3 = c1.num3 + c2.num3)
    case Number1T =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def countNumber2(num2: Number2): Count = num2 match {
    case Number2S(num1, num3) =>
      val c1 = countNumber1(num1)
      val c2 = countNumber3(num3)
      Count(num1 = c1.num1 + c2.num1, num2 = c1.num2 + c2.num2 + 1, num3 = c1.num3 + c2.num3)
    case Number2T =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def countNumber3(num3: Number3): Count = num3 match {
    case Number3S(num1, num2) =>
      val c1 = countNumber1(num1)
      val c2 = countNumber2(num2)
      Count(num1 = c1.num1 + c2.num1, num2 = c1.num2 + c2.num2, num3 = c1.num3 + c2.num3 + 1)
    case Number3T =>
      Count(num1 = 0, num2 = 0, num3 = 0)
  }

  def main(arr: Array[String]): Unit = {
    val n = countNumber1(numAny)
    println(n)
  }

}
