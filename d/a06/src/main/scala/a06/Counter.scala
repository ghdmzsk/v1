package a06

trait Number1_1 {
  def method1(num2: Number2_1): Number3_1
}
case class Number1_1S(tail1: Number1_1) extends Number1_1 {
  override def method1(num2: Number2_1): Number3_1 = Number3_1S(() => tail1.method1(num2), false, false)
}
case class Number1_1T(tail1: Number1_1, tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2_1): Number3_1 = Number3_1S(() => num2.method3(tail1, tail2), false, false)
}
case class Number1_1U(tail1: () => Number1_1, tail2: () => Number1_2) extends Number1_1 {
  override def method1(num2: Number2_1): Number3_1 = num2.method3(tail1(), tail2())
}

trait Number1_2 {
  def method2(num2_1: Number2_1, num2_2: Number2_2): Number3_2
}
case class Number1_2S(tail2: Number1_2) extends Number1_2 {
  override def method2(num2_1: Number2_1, num2_2: Number2_2): Number3_2 = Number3_2S(() => tail2.method2(num2_1, num2_2), false, false)
}
case class Number1_2T(tail2: Number1_2, tail1: Number1_1) extends Number1_2 {
  override def method2(num2_1: Number2_1, num2_2: Number2_2): Number3_2 =
    Number3_2T(() => num2_2.method4(tail2), () => tail1.method1(num2_1), false, false)
}
case class Number1_2U(tail2: () => Number1_2, tail1: () => Number1_1) extends Number1_2 {
  override def method2(num2_1: Number2_1, num2_2: Number2_2): Number3_2 =
    Number3_2T(() => num2_2.method4(tail2()), () => Number3_1S(() => tail1().method1(num2_1), true, false), false, true)
}

// ====
trait Number2_1 {
  def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_1
}
case class Number2_1S(tail1: Number2_1) extends Number2_1 {
  override def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_1 = Number3_1S(() => tail1.method3(num1_1, num1_2), false, false)
}
case class Number2_1T(tail1: Number2_1, tail2: Number2_2) extends Number2_1 {
  override def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_1 =
    Number3_1T(() => num1_1.method1(tail1), () => tail2.method4(num1_2), false, false)
}
case class Number2_1U(tail1: () => Number2_1, tail2: () => Number2_2) extends Number2_1 {
  override def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_1 =
    Number3_1T(() => num1_1.method1(tail1()), () => Number3_2S(() => tail2().method4(num1_2), false, true), true, false)
}

trait Number2_2 {
  def method4(num1_2: Number1_2): Number3_2
}
case class Number2_2S(tail2: Number2_2) extends Number2_2 {
  override def method4(num1_2: Number1_2): Number3_2 = Number3_2S(() => tail2.method4(num1_2), false, false)
}
case class Number2_2T(tail2: Number2_2, tail1: Number2_1) extends Number2_2 {
  override def method4(num1_2: Number1_2): Number3_2 = Number3_2S(() => num1_2.method2(tail1, tail2), false, false)
}
case class Number2_2U(tail2: () => Number2_2, tail1: () => Number2_1) extends Number2_2 {
  override def method4(num1_2: Number1_2): Number3_2 = num1_2.method2(tail1(), tail2())
}

// ====
trait Number3_1
case class Number3_1S(tail1: () => Number3_1, end1: Boolean, end2: Boolean)                         extends Number3_1
case class Number3_1T(tail1: () => Number3_1, tail2: () => Number3_2, end1: Boolean, end2: Boolean) extends Number3_1

trait Number3_2
case class Number3_2S(tail2: () => Number3_2, end1: Boolean, end2: Boolean)                         extends Number3_2
case class Number3_2T(tail2: () => Number3_2, tail1: () => Number3_1, end1: Boolean, end2: Boolean) extends Number3_2
