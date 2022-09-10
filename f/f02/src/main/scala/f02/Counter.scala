package f02

trait Number3
trait Number3S(using() => Number4) extends Number3 {
  lazy val child: Number3
}
trait Number3T extends Number3

trait Number4 extends (Number3 => Number3 | Number4)
trait Number4S(val husband: Number3) extends Number4 {
  def birth: Number3 | Number4 = apply(husband)
}
trait Number4T(var tail: Number4) extends Number4 {
  override def apply(num3: Number3): Number3 | Number4 = tail(num3)
}
