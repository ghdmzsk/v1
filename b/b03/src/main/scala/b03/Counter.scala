package b03

trait Number1 {
  def method1(number2: Number2): Number2
}
case class Number1S(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number2 = number2.method2(tail)
}
case class Number1T(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number2 = Number2S(tail.method1(number2))
}
case object Number1U extends Number1 {
  override def method1(number2: Number2): Number2 = number2
}

trait Number2 {
  def method2(number1: Number1): Number2
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number1): Number2 = number1.method1(tail)
}
case object Number2T extends Number2 {
  override def method2(number1: Number1): Number2 = Number2T
}
