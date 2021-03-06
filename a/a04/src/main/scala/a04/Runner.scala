package a04

object Runner {

  lazy val number1ZeroU: Number5 = Number5U(() => number1ZeroU)

  def number1Gen7(n: Int): Number5 = if (n > 0) Number5S(number1Gen7(n - 1)) else number1ZeroU
  def number1Gen8(n: Int): Number6 = if (n > 0) Number6S(() => number1Gen8(n - 1)) else throw new Exception
  def number1Gen9(n: Int): Number5 = if (n > 0) Number5T(number1Gen9(n - 1)) else number1ZeroU

  def countNumber2(number: () => Number6): Int = {
    val next =
      try {
        Option(number())
      } catch {
        case _: Exception => Option.empty
      }
    next match {
      case Some(Number6S(tail)) => countNumber2(tail) + 1
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
          val number1 = number1Gen9(i1)
          def number2 = number1Gen8(i2)
          def number3 = number2.method2(number1)
          val count1  = countNumber2(() => number3)
          val result1 = if (i2 == 0) 0 else i1 + i2 - 1
          assert(count1 == result1)
          count1
        }

        val countNum2 = {
          val number1 = number1Gen9(i1)
          def number2 = number1Gen8(i2)
          def number3 = number1.method1(() => number2)
          val count1  = countNumber2(() => number3)
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
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val countNum3 = {
          val number1 = number1Gen7(i1)
          def number2 = number1Gen8(i2)
          def number3 = number1.method1(() => number2)
          val count1  = countNumber2(() => number3)
          val result1 = if (i2 - i1 > 0) i2 - i1 else 0
          assert(count1 == result1)
          count1
        }

        val countNum4 = {
          val number1 = number1Gen7(i1)
          def number2 = number1Gen8(i2)
          def number3 = number2.method2(number1)
          val count1  = countNumber2(() => number3)
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
