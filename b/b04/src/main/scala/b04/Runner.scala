package b04

object Runner {

  lazy val number1ZeroU: Number1 = Number1U(() => number1ZeroU)

  def number1Gen1(n: Int): Number1                   = if (n > 0) Number1T(number1Gen1(n - 1)) else Number1S(() => number1ZeroU)
  def number1Gen2(n: Int): Number1                   = if (n > 0) Number1S(() => number1Gen2(n - 1)) else Number1S(() => number1ZeroU)
  def number1Gen3(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(number1Gen3(n - 1, zero)) else zero
  def number2Gen(n: Int): Number2                    = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  def count(number: Number3): Int = number match {
    case Number3S(tail) => count(tail) + 1
    case Number3T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val number1 = number1Gen1(i1)
        val number2 = number2Gen(i2)
        val number3 = number1.method1(number2)
        val count1  = count(number3)
        val result1 = i1 + i2
        assert(count1 == result1)
      }
    }
    {
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val number1 = number1Gen2(i1)
        val number2 = number2Gen(i2)
        val number3 = number1.method1(number2)
        val count1  = count(number3)
        val result1 = if (i2 - i1 > 0) i2 - i1 else 0
        assert(count1 == result1)
      }
    }
    {
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        lazy val number1s: Number1 = number1Gen3(i1, number1t)
        lazy val number1t: Number1 = Number1S(() => number1s)
        val number2                = number2Gen(i2)
        val number3                = number1t.method1(number2)
        val count1                 = count(number3)
        val result1                = i1 * i2
        assert(count1 == result1)
      }
    }
  }

}
