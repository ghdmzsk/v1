package a10

trait Number1
case class Number1S(tail: Number1, head: Number2) extends Number1
case object Number1T                              extends Number1

trait Number2
case class Number2S(tail: Number2, head: Number1) extends Number2
case object Number2T                              extends Number2
