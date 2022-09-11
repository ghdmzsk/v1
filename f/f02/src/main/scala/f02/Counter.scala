package f02

trait Number3
trait Number3S extends Number3:
  lazy val child: Number3
end Number3S
trait Number3T(using Number4) extends Number3S:
  override lazy val child: Number3
end Number3T

trait Number4                 extends (Number3 => Number3T | Number4)
trait Number4S(using Number4) extends Number4
trait Number4T(val husband: () => Number3) extends Number4:
  def birth: Number3T | Number4 = apply(husband())
end Number4T
