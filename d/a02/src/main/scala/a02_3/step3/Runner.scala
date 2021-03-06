package a02_3.step3

object Runner {

  def count1(number: Number1): Int = number match {
    case Number1S(tail, head) => count1(tail) + count2(head) + 1
    case Number1T             => 0
  }

  def count2(number: Number2): Int = number match {
    case Number2S(tail, head) => count2(tail) + count1(head)
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
    val number2 =
      Number1S(Number1T, Number2S(Number2T, Number1S(Number1T, Number2S(Number2S(Number2S(Number2T, Number1T), Number1T), Number1T))))
    val number3 = number1.method1(number2)

    val input1  = count1(number1)
    val input2  = count1(number2)
    val result1 = count1(number3)

    assert(input1 == 7)
    assert(input2 == 2)
    assert(input1 + input2 == result1)

    for {
      i <- 1 to 15
      _ <- 1 to 3
    } {
      val num1 = genNumber1(-i)
      val num2 = genNumber1(-i - 1)
      val num3 = num1.method1(num2)

      val n1 = count1(num1)
      val n2 = count1(num2)
      val n3 = count1(num3)

      assert(n1 + n2 == n3)
      println(s"$n1 + $n2 = $n3 ${n3 == n1 + n2}")

//107 + 166 = 273 false
//1 + 0 = 1 false
//40 + 0 = 40 false
//96 + 0 = 96 false
//85 + 629 = 714 false
//0 + 400 = 400 false
//254 + 243 = 497 false
//319 + 865 = 1184 false
//78 + 629 = 707 false
//318 + 1817 = 2135 false
//0 + 0 = 0 false
//547 + 0 = 547 false
//2 + 2923 = 2925 false
//1388 + 4010 = 5398 false
//844 + 3103 = 3947 false
//1760 + 0 = 1760 false
//3850 + 0 = 3850 false
//5145 + 3818 = 8963 false
//1993 + 5615 = 7608 false
//0 + 0 = 0 false
//0 + 14873 = 14873 false
//4047 + 29788 = 33835 false
//16143 + 0 = 16143 false
//2935 + 24085 = 27020 false
//12147 + 88473 = 100620 false
//8004 + 15937 = 23941 false
//498 + 48685 = 49183 false
//64026 + 0 = 64026 false
//62808 + 83814 = 146622 false
//0 + 59043 = 59043 false
//73005 + 249335 = 322340 false
//30431 + 83294 = 113725 false
//62663 + 67100 = 129763 false
//0 + 429998 = 429998 false
//237398 + 538770 = 776168 false
//159658 + 528589 = 688247 false
//337924 + 842984 = 1180908 false
//318255 + 0 = 318255 false
//151210 + 0 = 151210 false
//341124 + 335685 = 676809 false
//0 + 1347852 = 1347852 false
//2 + 2239593 = 2239595 false
//428023 + 0 = 428023 false
//0 + 1112867 = 1112867 false
//1933089 + 2365072 = 4298161 false
    }
  }

}
