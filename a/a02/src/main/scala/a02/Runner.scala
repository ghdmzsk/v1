package a02

object Runner {

  lazy val number1ZeroU: Number1 = Number1U(() => number1ZeroU)

  def number1Gen1(n: Int): Number1                   = if (n > 0) Number1T(number1Gen1(n - 1)) else number1ZeroU
  def number1Gen3(n: Int): Number1                   = if (n > 0) Number1S(() => number1Gen3(n - 1)) else number1ZeroU
  def number1Gen4(n: Int): Number1                   = if (n > 0) Number1S(() => number1Gen4(n - 1)) else number1ZeroU
  def number1Gen5(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(number1Gen5(n - 1, zero)) else zero
  def number1Gen6(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1Gen6(n - 1, zero)) else zero
  def number1Gen7(n: Int): Number5                   = if (n > 0) Number5S(number1Gen7(n - 1)) else Number5U
  def number1Gen8(n: Int): Number6                   = if (n > 0) Number6S(number1Gen8(n - 1)) else Number6T
  def number1Gen9(n: Int): Number5                   = if (n > 0) Number5T(number1Gen9(n - 1)) else Number5U
  def number2Gen(n: Int): Number2                    = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  def count(number: Number3): Int = number match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def countNumber6(number: Number6): Int = number match {
    case Number6S(tail) => countNumber6(tail) + 1
    case Number6T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      // 加法
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        if (i2 > 0) {
          val countNum1 = {
            val number1 = number1Gen1(i1)
            val number2 = number2Gen(i2)
            val number3 = number2.method2(number1)
            val count1  = count(number3)
            val result1 = i1 + i2
            assert(count1 == result1)
            count1
          }

          val countNum2 = {
            val number1 = number1Gen9(i1)
            val number2 = number1Gen8(i2)
            val number3 = number2.method2(number1)
            val count1  = countNumber6(number3)
            val result1 = i1 + i2 - 1
            assert(count1 == result1)
            count1
          }

          assert(countNum1 == countNum3)
          assert(countNum1 == countNum2 + 1)
        }

        lazy val countNum3 = {
          val number1 = number1Gen9(i1)
          val number2 = number1Gen8(i2)
          val number3 = number1.method1(number2)
          val count1  = countNumber6(number3)
          val result1 = i1 + i2
          assert(count1 == result1)
          count1
        }

        val countNum4 = {
          val number1 = number1Gen1(i1)
          val number2 = number2Gen(i2)
          val number3 = number1.method1(number2)
          val count1  = count(number3)
          val result1 = i1 + i2 + 1
          assert(count1 == result1)
          count1
        }

        assert(countNum3 == countNum4 - 1)
      }
    }

    {
      // 减法
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val countNum1 = {
          val number1 = number1Gen4(i1)
          val number2 = number2Gen(i2)
          val number3 = number2.method2(number1)
          val count1  = count(number3)
          val result1 = if (i2 - i1 > 0) i2 - i1 else 0
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          val number1 = number1Gen3(i1)
          val number2 = number2Gen(i2)
          val number3 = number1.method1(number2)
          val count1  = count(number3)
          val result1 = if (i2 - i1 + 1 > 0) i2 - i1 + 1 else 0
          assert(count1 == result1)
          count1
        }

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

        assert(countNum1 == countNum3)

        if (i2 - i1 >= 0)
          assert(countNum1 == countNum2 - 1)
        else
          assert(countNum1 == countNum2)

        if (i2 - i1 > 0)
          assert(countNum3 == countNum4 + 1)
        else
          assert(countNum3 == countNum4)
      }
    }

    {
      // 乘法
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val countNum1 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          val number2: Number2       = number2Gen(i2)
          val number3                = number2.method2(number1s)
          val count1                 = count(number3)
          val result1                = i1 * i2
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          val number2: Number2       = number2Gen(i2)
          val number3                = number1t.method1(number2)
          val count1                 = count(number3)
          val result1                = i1 * i2
          assert(count1 == result1)
          count1
        }

        val countNum3 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          val number2: Number2       = number2Gen(i2)
          val number3                = number1s.method1(number2)
          val count1                 = count(number3)
          val result1                = i1 * (i2 + 1)
          assert(count1 == result1)
          count1
        }

        if (i2 > 0) {
          val countNum4 = {
            lazy val number1s: Number1 = number1Gen5(i1, number1t)
            lazy val number1t: Number1 = Number1S(() => number1s)
            val number2: Number2       = number2Gen(i2)
            val number3                = number2.method2(number1t)
            val count1                 = count(number3)
            val result1                = i1 * (i2 - 1)
            assert(count1 == result1)
            count1
          }

          assert(countNum2 == countNum4 + i1)
        }

        assert(countNum1 == countNum2)
        assert(countNum1 == countNum3 - i1)
      }
    }

    {
      // 除法
      for {
        i1 <- 1 to 100
        i2 <- 0 to 100
      } {
        val countNum1 = {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(number1s)
          val number2s: Number2      = number2Gen(i2)
          val number3                = number1t.method1(number2s)
          val count1                 = count(number3)
          val result1                = (i2 + i1) / i1
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(number1s)
          val number2: Number2       = number2Gen(i2)
          val number3                = number1s.method1(number2)
          val count1                 = count(number3)
          val result1                = i2 / i1
          assert(count1 == result1)
          count1
        }

        val countNum3 = {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(number1s)
          val number2: Number2       = number2Gen(i2)
          val number3                = number2.method2(number1t)
          val count1                 = count(number3)
          val result1                = if (i2 % i1 == 0) i2 / i1 else i2 / i1 + 1
          val result2                = (i2 + i1 - 1) / i1
          assert(result1 == result2)
          assert(count1 == result1)
          count1
        }

        if (i2 > 0) {
          val countNum4 = {
            lazy val number1s: Number1 = number1Gen6(i1, number1t)
            lazy val number1t: Number1 = Number1T(number1s)
            val number2: Number2       = number2Gen(i2)
            val number3                = number2.method2(number1s)
            val count1                 = count(number3)
            val result1                = (i2 - 1) / i1
            assert(count1 == result1)
            count1
          }

          assert(countNum3 == countNum4 + 1)
        }

        if (i2 % i1 == 0)
          assert(countNum2 == countNum3)
        else
          assert(countNum2 == countNum3 - 1)

        assert(countNum2 == countNum1 - 1)
      }
    }
  }

}
