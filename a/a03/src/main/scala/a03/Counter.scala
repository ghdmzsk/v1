package a03

trait Length[T <: Number1] {
  def length: Int
}

trait Number1

class Number1S[T <: Number1] extends Number1
object Number1S {
  implicit def number1sImplicit[T <: Number1](implicit n: Length[T]): Length[Number1S[T]] = new Length[Number1S[T]] {
    override def length: Int = n.length + 1
  }
}

class Number1T extends Number1
object Number1T {
  implicit val number1tImplicit: Length[Number1T] = new Length[Number1T] {
    override def length: Int = 0
  }
}

class Plus[P1 <: Number1, P2 <: Number2, P3 <: Number1]
class Minus[P1 <: Number1, P2 <: Number2, P3 <: Number1]

trait Number2

class Number2S[T <: Number2] extends Number2
object Number2S {
  implicit def number2sImplicit[T1 <: Number1, T2 <: Number2, T3 <: Number1](implicit
    n: Plus[T1, T2, T3]
  ): Plus[T1, Number2S[T2], Number1S[T3]] = new Plus

  implicit def number2sImplicitMinus[T1 <: Number1, T2 <: Number2, T3 <: Number1](implicit
    n: Minus[T1, T2, T3]
  ): Minus[Number1S[T1], Number2S[T2], T3] = new Minus
  implicit def number2sImplicitMinus1[T1 <: Number1, T2 <: Number2, T3 <: Number1](implicit
    n: Minus[T1, T2, T3]
  ): Minus[Number1T, Number2S[T2], Number1T] = new Minus
}

class Number2T extends Number2
object Number2T {
  implicit def number2tImplicit[T1 <: Number1]: Plus[T1, Number2T, T1] = new Plus[T1, Number2T, T1]

  implicit def number2tImplicitMinus[T1 <: Number1]: Minus[T1, Number2T, T1] = new Minus
}
