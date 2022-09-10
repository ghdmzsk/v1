package f02

trait Number3
trait Number3S(using Number4) extends Number3

trait Number4
trait Number4S(using() => Number3, Number3 | Number4) extends Number4
