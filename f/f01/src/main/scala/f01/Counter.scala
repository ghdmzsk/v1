package f01

trait Number3:
  def size: Int
  def add: Number3
end Number3

// 零
object Number3S extends Number3:
  override def size: Int = 0
  override def add: Number3 =
    lazy val t0: Number4  = Number4S(() => t1)
    lazy val t1: Number3T = Number3T1(t0)
    t1
  end add
end Number3S

// 考虑后继特性
trait Number4(next: () => Number3):
  def preSize: Int
end Number4
case class Number4S(next: () => Number3) extends Number4(next):
  override def preSize: Int = 0
end Number4S

// 有前驱
trait Number3T(pre: Number4) extends Number3:
  override def size: Int
  override def add: Number3 =
    lazy val t1: Number4  = Number3ST(pre, () => t2)
    lazy val t2: Number3T = Number3T1(t1)
    t2
  end add
end Number3T
// 不考虑后继特性
case class Number3T1(pre: Number4) extends Number3T(pre):
  override def size: Int = pre.preSize + 1
end Number3T1

// 既有前驱又考虑后继特性
case class Number3ST(pre: Number4, next: () => Number3) extends Number3T(pre), Number4(next):
  override def preSize: Int = pre.preSize + 1
  override def size: Int    = next().size - 1
end Number3ST

object Number3:
  def fromInt(n: Int): Number3 = if (n > 0) fromInt(n - 1).add else Number3S
end Number3
