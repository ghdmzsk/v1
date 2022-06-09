package a08

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.StdIn

object Runner {

  lazy val number1ZeroU: Number1 = Number1U(() => Future(number1ZeroU))
  lazy val number2ZeroT: Number2 = Number2T(() => Future(number2ZeroT))

  def number1Gen1(n: Int): Number1                   = if (n > 0) Number1T(() => Future(number1Gen1(n - 1))) else number1ZeroU
  def number1Gen3(n: Int): Number1                   = if (n > 0) Number1S(() => Future(number1Gen3(n - 1))) else number1ZeroU
  def number1Gen4(n: Int): Number1                   = if (n > 0) Number1S(() => Future(number1Gen4(n - 1))) else number1ZeroU
  def number1Gen5(n: Int, zero: => Number1): Number1 = if (n > 0) Number1T(() => Future(number1Gen5(n - 1, zero))) else zero
  def number1Gen6(n: Int, zero: => Number1): Number1 = if (n > 0) Number1S(() => Future(number1Gen6(n - 1, zero))) else zero
  def number2Gen(n: Int): Number2                    = if (n > 0) Number2S(() => Future(number2Gen(n - 1))) else number2ZeroT

  val countContext = new NumberCount

  def count: Unit = {
    var num = 0
    countContext.map.foreach { case (i, v) =>
      num += 1
      assert(i.except == v)
    }
    println(s"统计了 $num 个结果")
  }

  def main(arr: Array[String]): Unit = {
    {
      // 加法
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        {
          val number1 = number1Gen1(i1)
          def number2 = number2Gen(i2)
          val result1 = Input(i1, i2, if (i2 == 0) 0 else i1 + i2, "加法1")(countContext)
          number2.method2(number1, result1)
        }

        {
          val number1 = number1Gen1(i1)
          def number2 = number2Gen(i2)
          val result1 = Input(i1, i2, i1 + i2 + 1, "加法2")(countContext)
          number1.method1(number2, result1)
        }
      }
    }

    {
      // 减法
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        {
          val number1 = number1Gen4(i1)
          val number2 = number2Gen(i2)
          val result1 = Input(i1, i2, if (i2 - i1 > 0) i2 - i1 else 0, "减法1")(countContext)
          number2.method2(number1, result1)
        }

        {
          val number1 = number1Gen3(i1)
          val number2 = number2Gen(i2)
          val result1 = Input(i1, i2, if (i2 - i1 + 1 > 0) i2 - i1 + 1 else 0, "减法2")(countContext)
          number1.method1(number2, result1)
        }

      }
    }

    {
      // 乘法
      for {
        i1 <- 0 to 20
        i2 <- 0 to 20
      } {
        {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => Future(number1s))
          val number2: Number2       = number2Gen(i2)
          val result1                = Input(i1, i2, i1 * i2, "乘法1")(countContext)
          number2.method2(number1s, result1)
        }

        {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => Future(number1s))
          val number2: Number2       = number2Gen(i2)
          val result1                = Input(i1, i2, i1 * i2, "乘法2")(countContext)
          number1t.method1(number2, result1)
        }

        {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => Future(number1s))
          val number2: Number2       = number2Gen(i2)
          val result1                = Input(i1, i2, i1 * (i2 + 1), "乘法3")(countContext)
          number1s.method1(number2, result1)
        }

        {
          lazy val number1s: Number1 = number1Gen5(i1, number1t)
          lazy val number1t: Number1 = Number1S(() => Future(number1s))
          val number2: Number2       = number2Gen(i2)
          val result1                = Input(i1, i2, if (i2 == 0) 0 else i1 * (i2 - 1), "乘法4")(countContext)
          number2.method2(number1t, result1)
        }
      }
    }

    {
      // 除法
      for {
        i1 <- 1 to 20
        i2 <- 0 to 20
      } {
        {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(() => Future(number1s))
          val number2                = number2Gen(i2)
          val result1                = Input(i1, i2, (i2 + i1) / i1, "除法1")(countContext)
          number1t.method1(number2, result1)
        }

        {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(() => Future(number1s))
          val number2                = number2Gen(i2)
          val result1                = Input(i1, i2, i2 / i1, "除法2")(countContext)
          number1s.method1(number2, result1)
        }

        {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(() => Future(number1s))
          val number2                = number2Gen(i2)
          val result1                = Input(i1, i2, (i2 + i1 - 1) / i1, "除法3")(countContext)
          number2.method2(number1t, result1)
        }

        if (i2 > 0) {
          lazy val number1s: Number1 = number1Gen6(i1, number1t)
          lazy val number1t: Number1 = Number1T(() => Future(number1s))
          val number2                = number2Gen(i2)
          val result1                = Input(i1, i2, (i2 - 1) / i1, "除法4")(countContext)
          number2.method2(number1s, result1)
        }
      }
    }

    println("Press any key to count.")
    StdIn.readLine()

    count
  }

}
