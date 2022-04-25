package a13

trait Number1_1 {
  def method1(num2: Number2_2): Number3_1
}
case class Number1_1S(tail1: Number1_1, tail2: Number1_2) extends Number1_1 {
  override def method1(num2: Number2_2): Number3_1 = Number3_1S(num2.method2(tail1), num3.method3(tail2))
}
case object Number1_1T extends Number1_1 {
  override def method1(num2: Number2_2): Number3_1 = Number3_1T
}

trait Number1_2 {
  def method2(num3: Number2_1): Number3_2
}
case class Number1_2S(tail2: Number1_2, tail3: Number1_3) extends Number1_2 {
  override def method2(num3: Number2_1): Number3_2 = Number3_2S(num1.method1(tail3), num3.method3(tail2))
}
case object Number1_2T extends Number1_2 {
  override def method2(num3: Number2_1): Number3_2 = Number3_2T
}

trait Number1_3 {
  def method3(num1: Number2_1): Number3_3
}
case class Number1_3S(tail3: Number1_3, tail1: Number1_1) extends Number1_3 {
  override def method3(num1: Number2_1): Number3_3 = Number3_3S(num1.method1(tail3), num2.method2(tail1))
}
case object Number1_3T extends Number1_3 {
  override def method3(num1: Number2_1): Number3_3 = Number3_3T
}

// ====
trait Number2_1 {
  def method1(num3: Number1_3): Number3_3
}
case class Number2_1S(tail1: Number2_1, tail3: Number2_2) extends Number2_1 {
  override def method1(num3: Number1_3): Number3_3 = Number3_3S(tail1.method1(num3), num2.method2(tail3))
}
case class Number2_1T(tail1: () => Number2_1) extends Number2_1 {
  override def method1(num3: Number1_3): Number3_3 = num3.method3(tail1())
}

trait Number2_2 {
  def method2(num1: Number1_1,num2: Number1_2): Number3_3
}
case class Number2_2S(tail2: Number2_2, tail1: Number2_1) extends Number2_2 {
  override def method2(num1: Number1_1,num2: Number1_2): Number3_3 = Number3_3S(num1.method1(tail2), num2.method2(tail1))
}
case class Number2_2T(tail2: () => Number2_2) extends Number2_2 {
  override def method2(num1: Number1_1,num2: Number1_2): Number3_3 = tail2().method2(num1,num2)
}

// ====
trait Number3_1
case class Number3_1S(tail1: Number3_1, tail2: Number3_2) extends Number3_1
case object Number3_1T                                    extends Number3_1

trait Number3_2
case class Number3_2S(tail2: Number3_2, tail3: Number3_3) extends Number3_2
case object Number3_2T                                    extends Number3_2

trait Number3_3
case class Number3_3S(tail3: Number3_3, tail1: Number3_1) extends Number3_3
case object Number3_3T                                    extends Number3_3
