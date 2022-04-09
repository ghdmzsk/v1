package b01

object Runner {

  def number1Gen(n: Int): Number1 = if (n > 0) Number1S(number1Gen(n - 1)) else Number1T

  def count(number: Number1): Int = number match {
    case Number1S(tail) => count(tail) + 1
    case Number1T       => 0
  }

  def main(arr: Array[String]): Unit = {
    {
      for (i <- 0 to 100) {
        val number1 = number1Gen(i)
        val count1  = count(number1)
        assert(i == count1)
      }
    }
  }

}
