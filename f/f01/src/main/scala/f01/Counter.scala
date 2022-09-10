package f01

trait Number3:
  def size: Int
  lazy val add: Number3
end Number3

// 零
object Number3S extends Number3:
  override def size: Int = 0

  override lazy val add: Number3 =
    given (() => Number4)  = () => new Number4S(num3)
    lazy val num3: Number3 = new Number3T1
    num3
  end add
end Number3S

// 考虑后继特性
trait Number4(next: Number3):
  def preSize: Int
end Number4
class Number4S(next: Number3) extends Number4(next):
  override def preSize: Int = 0
end Number4S

// 有前驱
trait Number3T(using() => Number4) extends Number3:
  override def size: Int

  lazy val addImpl: Number4 = new Number3ST(add)

  override lazy val add: Number3 =
    given (() => Number4)  = () => addImpl
    lazy val num3: Number3 = new Number3T1
    num3
  end add
end Number3T
// 不考虑后继特性
class Number3T1(using() => Number4) extends Number3T:
  lazy val pre: Number4  = summon[() => Number4]()
  override def size: Int = pre.preSize + 1
end Number3T1

// 既有前驱又考虑后继特性
class Number3ST(next: Number3)(using() => Number4) extends Number3T, Number4(next):
  lazy val pre: Number4     = summon[() => Number4]()
  override def preSize: Int = pre.preSize + 1
  override def size: Int    = next.size - 1
end Number3ST

object Number3:
  def fromInt(n: Int): Number3 = if (n > 0) fromInt(n - 1).add else Number3S
end Number3
