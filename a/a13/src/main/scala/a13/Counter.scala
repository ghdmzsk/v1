package a13

trait Number1 {
  def method1(num2: Number2, num3: Number3): Number1
}
case class Number1S(tail2: Number2, tail3: Number3) extends Number1 {
  override def method1(num2: Number2, num3: Number3): Number1 = Number1S(num2.method2(tail3), num3.method3(tail2))
}
case class Number1T(tail1: () => Number1) extends Number1 {
  override def method1(num2: Number2, num3: Number3): Number1 = tail1().method1(num2, num3)
}
case object Number1U extends Number1 {
  override def method1(num2: Number2, num3: Number3): Number1 = Number1U
}

trait Number2 {
  def method2(num3: Number3): Number2
}
case class Number2S(tail1: Number1, tail2: Number2) extends Number2 {
  override def method2(num3: Number3): Number2 = Number2S(tail1.method1(tail2, num3), tail2.method2(num3))
}
case class Number2T(tail2: () => Number2) extends Number2 {
  override def method2(num3: Number3): Number2 = tail2().method2(num3)
}
case object Number2U extends Number2 {
  override def method2(num3: Number3): Number2 = Number2U
}

trait Number3 {
  def method3(num2: Number2): Number3
}
case class Number3S(tail1: Number1, tail3: Number3) extends Number3 {
  override def method3(num2: Number2): Number3 = Number3S(tail1.method1(num2, tail3), tail3.method3(num2))
}
case class Number3T(tail3: () => Number3) extends Number3 {
  override def method3(num2: Number2): Number3 = tail3().method3(num2)
}
case object Number3U extends Number3 {
  override def method3(num2: Number2): Number3 = Number3U
}
