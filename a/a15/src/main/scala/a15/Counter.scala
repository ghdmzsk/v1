package a15

trait Number1_1 {
  def method1(num2: Number2): Number3_2
}
case class Number1_1S(tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2): Number3_2 = num2.method3(tail2)
}
case object Number1_1T extends Number1_1 {
  override def method1(num2: Number2): Number3_2 = Number3_2U
}

trait Number1_2 {
  def method2(num2: Number2): Number3_2
}
case class Number1_2S(tail2: Number1_2, tail1: Number1_1) extends Number1_2 {
  override def method2(num2: Number2): Number3_2 = Number3_2S(tail2.method2(num2), tail1.method1(num2))
}
case object Number1_2T extends Number1_2 {
  override def method2(num2: Number2): Number3_2 = Number3_2U
}

// ====
trait Number2 {
  def method3(num1: Number1_2): Number3_2
}
case class Number2S(tail: Number2) extends Number2 {
  override def method3(num1: Number1_2): Number3_2 = Number3_2T(Number3_1S(tail.method3(num1)))
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method3(num1: Number1_2): Number3_2 = num1.method2(tail())
}

// ====
trait Number3_1
case class Number3_1S(tail: Number3_2) extends Number3_1

trait Number3_2
case class Number3_2S(tail2_1: Number3_2, tail2_2: Number3_2) extends Number3_2
case class Number3_2T(tail: Number3_1)                        extends Number3_2
case object Number3_2U                                        extends Number3_2
