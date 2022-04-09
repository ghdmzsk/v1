package b01

trait Number1
case class Number1S(tail: Number1) extends Number1
case object Number1T               extends Number1
