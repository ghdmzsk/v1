package a03

object Runner {

  lazy val number1ZeroU: Number1 = Number1U(() => number1ZeroU)

  def number1Gen1(n: Int): Number1                   = if (n > 0) Number1T(number1Gen1(n - 1)) else number1ZeroU
  def number1Gen3(n: Int): Number1                   = if (n > 0) Number1S(() => number1Gen3(n - 1)) else number1ZeroU
  def number1Gen4(n: Int): Number1                   = if (n > 0) Number1S(() => number1Gen4(n - 1)) else number1ZeroU
  def number1Gen5(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(number1Gen5(n - 1, zero)) else zero
  def number1Gen6(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => number1Gen6(n - 1, zero)) else zero
  def number2Gen(n: Int): Number2                    = if (n > 0) Number2S(() => number2Gen(n - 1)) else throw new Exception

  def count(number: () => Number3): Int = {
    val next =
      try {
        Option(number())
      } catch {
        case _: Exception => Option.empty
      }
    next match {
      case Some(Number3S(tail)) => count(tail) + 1
      case None                 => 0
    }
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
          def number2 = number2Gen(i2)
          def number3 = number2.method2(number1)
          val count1  = count(() => number3)
          val result1 = if (i2 == 0) 0 else i1 + i2
          assert(count1 == result1)
          count1
        }

        val countNum2: Int = {
          val number1 = number1Gen1(i1)
          def number2 = number2Gen(i2)
          def number3 = number1.method1(() => number2)
          val count1  = count(() => number3)
          val result1 = i1 + i2 + 1
          assert(count1 == result1)
          count1
        }

        if (i2 == 0)
          assert(countNum2 == countNum1 + i1 + 1)
        else
          assert(countNum2 == countNum1 + 1)
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
          def number2 = number2Gen(i2)
          def number3 = number2.method2(number1)
          val count1  = count(() => number3)
          val result1 = if (i2 - i1 > 0) i2 - i1 else 0
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          val number1 = number1Gen3(i1)
          def number2 = number2Gen(i2)
          def number3 = number1.method1(() => number2)
          val count1  = count(() => number3)
          val result1 = if (i2 - i1 + 1 > 0) i2 - i1 + 1 else 0
          assert(count1 == result1)
          count1
        }

        if (i2 - i1 >= 0)
          assert(countNum1 == countNum2 - 1)
        else
          assert(countNum1 == countNum2)
      }
    }

    {
      // 乘法
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        val countNum1 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          def number2: Number2       = number2Gen(i2)
          def number3                = number2.method2(number1s)
          val count1                 = count(() => number3)
          val result1                = i1 * i2
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          def number2: Number2       = number2Gen(i2)
          def number3                = number1t.method1(() => number2)
          val count1                 = count(() => number3)
          val result1                = i1 * i2
          assert(count1 == result1)
          count1
        }

        val countNum3 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          def number2: Number2       = number2Gen(i2)
          def number3                = number1s.method1(() => number2)
          val count1                 = count(() => number3)
          val result1                = i1 * (i2 + 1)
          assert(count1 == result1)
          count1
        }

        val countNum4 = {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => number1s)
          def number2: Number2       = number2Gen(i2)
          def number3                = number2.method2(number1t)
          val count1                 = count(() => number3)
          val result1                = if (i2 == 0) 0 else i1 * (i2 - 1)
          assert(count1 == result1)
          count1
        }

        if (i2 == 0)
          assert(countNum2 == countNum4)
        else
          assert(countNum2 == countNum4 + i1)

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
          def number2                = number2Gen(i2)
          def number3                = number1t.method1(() => number2)
          val count1                 = count(() => number3)
          val result1                = (i2 + i1) / i1
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(number1s)
          def number2                = number2Gen(i2)
          def number3                = number1s.method1(() => number2)
          val count1                 = count(() => number3)
          val result1                = i2 / i1
          assert(count1 == result1)
          count1
        }

        val countNum3 = {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(number1s)
          def number2                = number2Gen(i2)
          def number3                = number2.method2(number1t)
          val count1                 = count(() => number3)
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
            def number2                = number2Gen(i2)
            def number3                = number2.method2(number1s)
            val count1                 = count(() => number3)
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
