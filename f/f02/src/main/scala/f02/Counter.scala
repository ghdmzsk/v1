package f02

trait Number3
trait Number3S extends Number3
trait Number3T extends Number3 {
  lazy val child: Number3
}
trait Number3U(using Number4) extends Number3T {
  override lazy val child: Number3
}

trait Number4(using Number4) extends (Number3 => Number3U | Number4)
trait Number4S(using Number4)(val husband: () => Number3) extends Number4 {
  def birth: Number3U | Number4 = apply(husband())
}
