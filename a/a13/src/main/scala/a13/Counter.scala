package a13

trait Number1 {
  def method1(num2: Number2, num3: Number3): Number1
}
case class Number1S(tail2: () => Number2, tail3: () => Number3) extends Number1 {
  def method1(num2: Number2, num3: Number3): Number1 = Number1S(() => tail2().method2(num3), () => tail3().method2(num2))
}
case object Number1T extends Number1 {
  def method1(num2: Number2, num3: Number3): Number1 = Number1S(() => num2, () => num3)
}

trait Number2 {
  def method2(num3: Number3): Number2
}
case class Number2S(tail2: Number2, tail1: Number1) extends Number2 {
  def method2(num3: Number3): Number2 = Number2S(tail2.method2(num3), tail1.method1(tail2, num3))
}
case class Number2T(tail2: () => Number2) extends Number2 {
  def method2(num3: Number3): Number2 = tail2().method2(num3)
}

trait Number3 {
  def method2(num2: Number2): Number3
}
case class Number3S(tail3: Number3, tail1: Number1) extends Number3 {
  def method2(num2: Number2): Number3 = Number3S(tail3.method2(num2), tail1.method1(num2, tail3))
}
case class Number3T(tail3: () => Number3) extends Number3 {
  def method2(num2: Number2): Number3 = tail3().method2(num2)
}
