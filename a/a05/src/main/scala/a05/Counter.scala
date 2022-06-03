package a05

trait Number5 {
  def method1(number6: Number6): Number6
}
case class Number5S(tail: Number5) extends Number5 {
  override def method1(number6: Number6): Number6 = number6.method2(tail)
}
case class Number5T(tail: Number5) extends Number5 {
  override def method1(number6: Number6): Number6 = Number6S(tail.method1(number6))
}
case class Number5U(tail: () => Number5) extends Number5 {
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
