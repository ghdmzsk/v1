package c01

object Runner {

  def count1(number: Number1): Int = number match {
    case Number1S(tail, head) => count1(tail) + count2(head) + 1
    case Number1T             => 0
  }

  def count2(number: Number2): Int = number match {
    case Number2S(tail, head) => count2(tail) + count1(head) - 1
    case Number2T             => 0
  }

  def randomDeep()   = math.abs(util.Random.nextInt() % 10)
  def randomLength() = math.abs(util.Random.nextInt() % 5)

  def genNumber1(deep: Int): Number1 = {
    if (deep > randomDeep())
      Number1T
    else {
      def genList1(length: Int): Number1 = if (length > 0) Number1S(genList1(length - 1), genNumber2(deep + 1)) else Number1T
      genList1(randomLength())
    }
  }

  def genNumber2(deep: Int): Number2 = {
    if (deep > randomDeep())
      Number2T
    else {
      def genList2(length: Int): Number2 = if (length > 0) Number2S(genList2(length - 1), genNumber1(deep + 1)) else Number2T
      genList2(randomLength())
    }
  }

  def main(arr: Array[String]): Unit = {
    val number1 = Number1S(
      Number1S(Number1S(Number1S(Number1S(Number1T, Number2T), Number2T), Number2T), Number2T),
      Number2S(Number2S(Number2S(Number2T, Number1S(Number1T, Number2S(Number2T, Number1S(Number1T, Number2T)))), Number1T), Number1T)
    )
    val number2 = Number2S(Number2T, Number1S(Number1T, Number2S(Number2S(Number2S(Number2T, Number1T), Number1T), Number1T)))
    val number3 = number1.method1(number2)
    val number4 = number2.method2(number1)

    val input1  = count1(number1)
    val input2  = count2(number2)
    val result1 = count2(number3)
    val result2 = count1(number4)

    println(input1)  // 3
    println(input2)  // -3
    println(result1) // 0
    println(result2) // -2
    assert(input1 + input2 == result1)

    for {
      i <- 1 to 15
      _ <- 1 to 3
    } {
      val num1 = genNumber1(-i)
      val num2 = genNumber2(-i - 1)
      val num3 = num1.method1(num2)

      val n1 = count1(num1)
      val n2 = count2(num2)
      val n3 = count2(num3)

      assert(n1 + n2 == n3)
      println(s"$n1 + $n2 = $n3 ${n3 == n1 + n2}")

// 4 + 0 = 4 true
// -5 + 9 = 4 true
// -2 + 5 = 3 true
// 0 + 4 = 4 true
// 23 + 12 = 35 true
// 0 + 18 = 18 true
// 5 + 0 = 5 true
// -11 + 7 = -4 true
// 0 + 0 = 0 true
// 0 + -5 = -5 true
// 19 + 48 = 67 true
// 0 + 6 = 6 true
// 1 + 25 = 26 true
// 1 + 0 = 1 true
// 51 + 0 = 51 true
// 41 + 102 = 143 true
// 41 + -2 = 39 true
// 42 + -91 = -49 true
// 0 + 0 = 0 true
// 1 + -5 = -4 true
// -124 + -3 = -127 true
// 1 + 11 = 12 true
// 213 + 158 = 371 true
// 0 + -93 = -93 true
// 130 + 21 = 151 true
// 137 + 20 = 157 true
// -172 + 0 = -172 true
// 474 + 304 = 778 true
// 0 + 0 = 0 true
// -27 + 416 = 389 true
// 171 + -437 = -266 true
// 0 + -456 = -456 true
// 35 + 513 = 548 true
// 163 + 657 = 820 true
// 0 + 508 = 508 true
// 600 + 162 = 762 true
// -341 + 0 = -341 true
// -438 + -941 = -1379 true
// 0 + -1 = -1 true
// 0 + 1620 = 1620 true
// 2138 + 753 = 2891 true
// 0 + 734 = 734 true
// 1 + -4813 = -4812 true
// 1 + -3458 = -3457 true
// -427 + 0 = -427 true
    }
  }

}
