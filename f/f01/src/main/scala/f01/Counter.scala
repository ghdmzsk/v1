package f01

trait Number3 {
  def size: Int
}

// 零
object Number3U extends Number3 {
  override def size: Int = 0
}

// 可以产生后继
trait Number3S {
  val next: () => Number3T
  def size: Int
}
case class Number3S1(override val next: () => Number3T) extends Number3S {
  override def size: Int = 0
}

// 有前驱
trait Number3T extends Number3 {
  def pre: Number3S
  override def size: Int = pre.size + 1
}
// 不考虑后继特性
case class Number3T1(override val pre: Number3S) extends Number3T

// 既有前驱又有后继
case class Number3ST(override val pre: Number3S, override val next: () => Number3T) extends Number3T with Number3S

object Number3 {

  def add(num: Number3): Number3T = num match {
    case Number3U =>
      lazy val t0: Number3S = Number3S1(() => t1)
      lazy val t1: Number3T = Number3T1(t0)
      t1
    case t: Number3T =>
      lazy val t1: Number3S = Number3ST(t.pre, () => t2)
      lazy val t2: Number3T = Number3T1(t1)
      t2
  }

  def fromInt(n: Int): Number3 = if (n > 0) add(fromInt(n - 1)) else Number3U

}
