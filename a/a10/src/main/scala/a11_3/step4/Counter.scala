package a11_3.step4

trait Number1
case class Number1S(tail: Number1, head: Number2) extends Number1
case object Number1T                              extends Number1

trait Number2 {
  def method1(number1: Number2): Number2
}
case class Number2S(tail: Number2, head: Number1) extends Number2 {
  override def method1(number2: Number2): Number2 = Number2S(tail.method1(number2), head)
}
case object Number2T extends Number2 {
  override def method1(number1: Number2): Number2 = number1
}
