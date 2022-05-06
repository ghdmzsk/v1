package a07

object Runner {

  def number1Gen1(n: Int): Number1 = if (n > 0) Number1T(number1Gen1(n - 1)) else Number1U
  def number1Gen2(n: Int): Number1 = if (n > 0) Number1S(number1Gen2(n - 1)) else Number1U
  def number2Gen(n: Int): Number2  = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  def count(number: Number2): Int = number match {
    case Number2S(tail) => count(tail) + 1
    case Number2T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      // 加法
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val countNum1 = {
          val number1 = number1Gen1(i1)
          val number2 = number2Gen(i2)
          val number3 = number1.method1(number2)
          val count1  = count(number3)
          val result1 = i1 + i2
          assert(count1 == result1)
          count1
        }

        if (i2 > 0) {
          val countNum2 = {
            val number1 = number1Gen1(i1)
            val number2 = number2Gen(i2)
            val number3 = number2.method2(number1)
            val count1  = count(number3)
            val result1 = i1 + i2 - 1
            assert(count1 == result1)
            count1
          }

          assert(countNum1 == countNum2 + 1)
        }
      }
    }
    {
      // 减法
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val countNum1 = {
          val number1 = number1Gen2(i1)
          val number2 = number2Gen(i2)
          val number3 = number1.method1(number2)
          val count1  = count(number3)
          val result1 = if (i2 - i1 > 0) i2 - i1 else 0
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          val number1 = number1Gen2(i1)
          val number2 = number2Gen(i2)
          val number3 = number2.method2(number1)
          val count1  = count(number3)
          val result1 = if (i2 - i1 - 1 > 0) i2 - i1 - 1 else 0
          assert(count1 == result1)
          count1
        }

        if (i2 - i1 > 0)
          assert(countNum1 == countNum2 + 1)
        else
          assert(countNum1 == countNum2)
      }
    }
  }

}
