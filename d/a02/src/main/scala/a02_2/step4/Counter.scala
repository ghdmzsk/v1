package a02_2.step4

trait Number1 {
  def method1(number2: Number2): Number1
}
case class Number1S(tail: Number1, head: Number2) extends Number1 {
  override def method1(number2: Number2): Number1 = Number1S(tail.method1(head), number2)
}
case object Number1T extends Number1 {
  override def method1(number2: Number2): Number1 = Number1S(Number1T, number2)
}

trait Number2
case class Number2S(tail: Number2, head: Number1) extends Number2
case object Number2T                              extends Number2
