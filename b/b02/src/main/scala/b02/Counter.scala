package b02

trait Number1 {
  def method1(number2: Number2): Number2
}
case class Number1S(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number2 = Number2S(tail.method1(number2))
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Number2 = number2
}

trait Number2
case class Number2S(tail: Number2) extends Number2
case object Number2T               extends Number2
