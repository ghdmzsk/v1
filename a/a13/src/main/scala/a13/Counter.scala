package a13

trait Number1 {
  def method1(num1: Number1): Number1
}
case class Number1S(tail2: () => Number2, tail3: () => Number3) extends Number1 {
  override def method1(num1: Number1): Number1 = Number1S(() => tail3().method2(num1), () => tail2().method2(num1))
}
case object Number1T extends Number1 {
  override def method1(num1: Number1): Number1 = Number1T
}

trait Number2 {
  def method2(num1: Number1): Number3
}
case class Number2S(tail3: Number3, tail1: Number1) extends Number2 {
  override def method2(num1: Number1): Number3 = Number3S(tail3.method2(num1), tail1.method1(num1))
}
case class Number2T(tail2: () => Number2) extends Number2 {
  override def method2(num1: Number1): Number3 = tail2().method2(num1)
}
case object Number2U extends Number2 {
  override def method2(num1: Number1): Number3 = Number3U
}

trait Number3 {
  def method2(num1: Number1): Number2
}
case class Number3S(tail2: Number2, tail1: Number1) extends Number3 {
  override def method2(num1: Number1): Number2 = Number2S(tail2.method2(num1), tail1.method1(num1))
}
case class Number3T(tail3: () => Number3) extends Number3 {
  override def method2(num1: Number1): Number2 = tail3().method2(num1)
}
case object Number3U extends Number3 {
  override def method2(num1: Number1): Number2 = Number2U
}
