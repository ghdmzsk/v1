package a05

trait Number1_1 {
  def method1(num2: Number2_1): Number3_1
}
case class Number1_1S(tail1: Number1_1, tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2_1): Number3_1 = Number3_1S(num2.method3(tail1, tail2))
}
case object Number1_1T extends Number1_1 {
  override def method1(num2: Number2_1): Number3_1 = Number3_1T(Number3_1U)
}

trait Number1_2 {
  def method2(num2: Number2_1): Number3_2
}
case class Number1_2S(tail2: Number1_2, tail1: Number1_1) extends Number1_2 {
  override def method2(num2: Number2_1): Number3_2 = Number3_2T(tail2.method2(num2), tail1.method1(num2))
}
case object Number1_2T extends Number1_2 {
  override def method2(num2: Number2_1): Number3_2 = Number3_2U(Number3_2W)
}

// ====
trait Number2_1 {
  def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_2
}
case class Number2_1S(tail1: Number2_1, tail2: Number2_2) extends Number2_1 {
  override def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_2 =
    Number3_2T(tail1.method3(num1_1, num1_2), tail2.method3(num1_1))
}
case class Number2_1T(tail1: Number2_1) extends Number2_1 {
  override def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_2 = Number3_2U(tail1.method3(num1_1, num1_2))
}
case class Number2_1U(tail: () => Number2_1) extends Number2_1 {
  override def method3(num1_1: Number1_1, num1_2: Number1_2): Number3_2 = num1_2.method2(tail())
}

trait Number2_2 {
  def method3(num1_1: Number1_1): Number3_1
}
case class Number2_2S(tail: Number2_2) extends Number2_2 {
  override def method3(num1_1: Number1_1): Number3_1 = Number3_1T(tail.method3(num1_1))
}
case class Number2_2T(tail: () => Number2_1) extends Number2_2 {
  override def method3(num1_1: Number1_1): Number3_1 = num1_1.method1(tail())
}

// ====
trait Number3_1
case class Number3_1S(tail: Number3_2) extends Number3_1
case class Number3_1T(tail: Number3_1) extends Number3_1
case object Number3_1U                 extends Number3_1

trait Number3_2
case class Number3_2T(tail2: Number3_2, tail1: Number3_1) extends Number3_2
case class Number3_2U(tail2: Number3_2)                   extends Number3_2
case object Number3_2W                                    extends Number3_2
