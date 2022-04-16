package a10

trait Number1 {
  def method1(num1: Number1): Number1
}
case object Number1_0 extends Number1 {
  def method1(num1: Number1): Number1 = num1
}
case class Number1_1(tail: Number1) extends Number1 {
  def method1(num1: Number1): Number1 = Number1_1(num1.method1(tail))
}
case class Number1_2(tail1: Number1, tail2: Number1) extends Number1 {
  def method1(num1: Number1): Number1 = Number1_2(num1.method1(tail1), tail2)
}
case class Number1_3(tail1: Number1, tail2: Number1, tail3: Number1) extends Number1 {
  def method1(num1: Number1): Number1 = Number1_3(num1.method1(tail1), tail2, tail3)
}
case class Number1_4(tail1: Number1, tail2: Number1, tail3: Number1, tail4: Number1) extends Number1 {
  def method1(num1: Number1): Number1 = Number1_4(num1.method1(tail1), tail2, tail3, tail4)
}
case class Number1_5(tail1: Number1, tail2: Number1, tail3: Number1, tail4: Number1, tail5: Number1) extends Number1 {
  def method1(num1: Number1): Number1 = Number1_5(num1.method1(tail1), tail2, tail3, tail4, tail5)
}
case class Number1_6(tail1: Number1, tail2: Number1, tail3: Number1, tail4: Number1, tail5: Number1, tail6: Number1) extends Number1 {
  def method1(num1: Number1): Number1 = Number1_6(num1.method1(tail1), tail2, tail3, tail4, tail5, tail6)
}
case class Number1_7(tail1: Number1, tail2: Number1, tail3: Number1, tail4: Number1, tail5: Number1, tail6: Number1, tail7: Number1)
    extends Number1 {
  def method1(num1: Number1): Number1 = Number1_7(num1.method1(tail1), tail2, tail3, tail4, tail5, tail6, tail7)
}
