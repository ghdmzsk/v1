package a10

trait Number1 {
  def method1(number2: Number2): Number2
}
case class Number1S(tail: Number2) extends Number1 {
  override def method1(number2: Number2): Number2 = Number2T(Number1S(Number2U(tail,number2)))
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Number2 = number2
}

trait Number2 {
  def method2(number1: Number1): Number1
}
case class Number2S(tail: Number2, head: Number1) extends Number2 {
  override def method2(number1: Number1): Number1 = Number1S(number1, head.method1(tail))
}
case class Number2T(tail: Number1) extends Number2 {
  override def method2(number1: Number1): Number1 = Number1S(number1, head.method1(tail))
}
case class Number2U(tail: Number2, head: Number2) extends Number2 {
  override def method2(number1: Number1): Number1 = Number1S(number1, head.method1(tail))
}
case object Number2T extends Number2 {
  override def method2(number1: Number1): Number1 = Number1S(number1, Number2T)
}
