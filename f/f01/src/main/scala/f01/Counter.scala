package f01

trait Number3 {
  def size: Int
}

// 零
object Number3U extends Number3 {
  override def size: Int = 0
}

// 有后继
trait Number3S extends Number3 {
  var next: Number3T = null
  override def size: Int
}
class Number3S1 extends Number3S {
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
case class Number3ST(override val pre: Number3S) extends Number3T with Number3S

object Number3 {

  def fromInt(n: Int): Number3 = {
    if (n > 0)
      Number3T1(fromIntImpl(n - 1))
    else Number3U
  }

  private def fromIntImpl(n: Int): Number3S = {
    if (n > 0) {
      val num     = fromIntImpl(n - 1)
      val numNext = Number3ST(num)
      num.next = numNext
      numNext
    } else new Number3S1
  }

}
