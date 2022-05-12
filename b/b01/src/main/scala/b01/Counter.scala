package b01

trait Number1 {
  def method1(number2: Number2): Number3
}
case class Number1S(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = number2.method2(tail())
}
case class Number1T(tail: Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(tail.method1(number2))
}
case class Number1U(tail: () => Number1) extends Number1 {
  override def method1(number2: Number2): Number3 = Number3S(number2.method2(tail()))
}

trait Number2 {
  def method2(number1: Number1): Number3
}
case class Number2S(tail: Number2) extends Number2 {
  override def method2(number1: Number1): Number3 = number1.method1(tail)
}
case object Number2T extends Number2 {
  override def method2(number1: Number1): Number3 = Number3T
}

trait Number3
case class Number3S(tail: Number3) extends Number3
case object Number3T               extends Number3

trait Number5 {
  def method1(number6: Number6): Number6
}
case class Number5S(tail: Number5) extends Number5 {
  override def method1(number6: Number6): Number6 = number6.method2(tail)
}
case class Number5T(tail: Number5) extends Number5 {
  override def method1(number6: Number6): Number6 = Number6S(tail.method1(number6))
}
case object Number5U extends Number5 {
  override def method1(number6: Number6): Number6 = number6
}

trait Number6 {
  def method2(number5: Number5): Number6
}
case class Number6S(tail: Number6) extends Number6 {
  override def method2(number5: Number5): Number6 = number5.method1(tail)
}
case object Number6T extends Number6 {
  override def method2(number5: Number5): Number6 = Number6T
}
