package b02

object Runner {

  def number1Gen(n: Int): Number1 = if (n > 0) Number1S(number1Gen(n - 1)) else Number1T
  def number2Gen(n: Int): Number2 = if (n > 0) Number2S(number2Gen(n - 1)) else Number2T

  def count(number: Number2): Int = number match {
    case Number2S(tail) => count(tail) + 1
    case Number2T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      for {
        i1 <- 0 to 100
        i2 <- 0 to 100
      } {
        val number1 = number1Gen(i1)
        val number2 = number2Gen(i2)
        val number3 = number1.method1(number2)
        val count1  = count(number3)
        val result1 = i1 + i2
        assert(count1 == result1)
      }
    }
  }

}
