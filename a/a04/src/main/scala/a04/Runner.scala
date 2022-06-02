package a04

object Runner {

  def number1Gen7(n: Int): Number5 = if (n > 0) Number5S(number1Gen7(n - 1)) else Number5U
  def number1Gen8(n: Int): Number6 = if (n > 0) Number6S(number1Gen8(n - 1)) else Number6T
  def number1Gen9(n: Int): Number5 = if (n > 0) Number5T(number1Gen9(n - 1)) else Number5U

  def countNumber6(number: Number6): Int = number match {
    case Number6S(tail) => countNumber6(tail) + 1
    case Number6T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      // 加法
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val countNum1 = {
          val number1 = number1Gen9(i1)
          val number2 = number1Gen8(i2)
          val number3 = number2.method2(number1)
          val count1  = countNumber6(number3)
          val result1 = if (i2 == 0) 0 else i1 + i2 - 1
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          val number1 = number1Gen9(i1)
          val number2 = number1Gen8(i2)
          val number3 = number1.method1(number2)
          val count1  = countNumber6(number3)
          val result1 = i1 + i2
          assert(count1 == result1)
          count1
        }

        if (i2 == 0)
          assert(countNum2 == countNum1 + i1)
        else
          assert(countNum2 == countNum1 + 1)
      }
    }

    {
      // 减法
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val countNum3 = {
          val number1 = number1Gen7(i1)
          val number2 = number1Gen8(i2)
          val number3 = number1.method1(number2)
          val count1  = countNumber6(number3)
          val result1 = if (i2 - i1 > 0) i2 - i1 else 0
          assert(count1 == result1)
          count1
        }

        val countNum4 = {
          val number1 = number1Gen7(i1)
          val number2 = number1Gen8(i2)
          val number3 = number2.method2(number1)
          val count1  = countNumber6(number3)
          val result1 = if (i2 - i1 - 1 > 0) i2 - i1 - 1 else 0
          assert(count1 == result1)
          count1
        }

        if (i2 - i1 > 0)
          assert(countNum3 == countNum4 + 1)
        else
          assert(countNum3 == countNum4)
      }
    }
  }

}
