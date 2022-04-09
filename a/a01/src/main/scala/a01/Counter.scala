package a01

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
