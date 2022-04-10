package b05

trait Number1 {
  def length: Int
}

case class Number1S(tail: Number1) extends Number1 {
  def length: Int = tail.length + 1
}

case object Number1T extends Number1 {
  def length: Int = 0
}
