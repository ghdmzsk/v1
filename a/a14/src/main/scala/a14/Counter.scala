package a14

trait Number1_1 {
  def method1(num2: Number2): Number3_1
}
case class Number1_1S(tail1: Number1_1, tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2): Number3_1 = Number3_1U(num2.method3(tail1), tail2.method2(num2))
}
case object Number1_1T extends Number1_1 {
  override def method1(num2: Number2): Number3_1 = Number3_1T
}

trait Number1_2 {
  def method2(num2: Number2): Number3_2
}
case class Number1_2S(tail2: Number1_2, tail1: Number1_1) extends Number1_2 {
  override def method2(num2: Number2): Number3_2 = Number3_2S(tail2.method2(num2), tail1.method1(num2))
}
case object Number1_2T extends Number1_2 {
  override def method2(num2: Number2): Number3_2 = Number3_2T
}

// ====
trait Number2 {
  def method3(num1: Number1_1): Number3_1
}
case class Number2S(tail: Number2) extends Number2 {
  override def method3(num1: Number1_1): Number3_1 = Number3_1S(tail.method3(num1), Number3_2T)
}
case class Number2T(tail: () => Number2) extends Number2 {
  override def method3(num1: Number1_1): Number3_1 = num1.method1(tail())
}

// ====
trait Number3_1
case class Number3_1S(tail1: Number3_1, tail2: Number3_2) extends Number3_1
case class Number3_1U(tail1: Number3_1, tail2: Number3_2) extends Number3_1
case object Number3_1T                                    extends Number3_1

trait Number3_2
case class Number3_2S(tail2: Number3_2, tail3: Number3_1) extends Number3_2
case object Number3_2T                                    extends Number3_2
