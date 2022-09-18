package f03

trait Number2
trait Number2S(using() => Number3) extends Number2

trait Number3
trait Number3S(val num2: Number2) extends Number3
