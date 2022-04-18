package a13

trait Number1
case class Number1S(num2: () => Number2, num3: () => Number3) extends Number1
case object Number1T                                          extends Number1

trait Number2
case class Number2S(num1: Number1, num3: Number3) extends Number2
case object Number2T                              extends Number2

trait Number3
case class Number3S(num1: Number1, num2: Number2) extends Number3
case object Number3T                              extends Number3
