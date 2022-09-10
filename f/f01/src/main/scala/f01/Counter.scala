package f01

trait Number3:
  def size: Int
  def add: Number3
end Number3

// 零
object Number3S extends Number3:
  override def size: Int = 0

  override def add: Number3 =
    given Number3          = new Number3T1(() => num4)
    lazy val num4: Number4 = new Number4S
    summon[Number3]
  end add
end Number3S

// 考虑后继特性
trait Number4(using Number3):
  def preSize: Int
end Number4
class Number4S(using Number3) extends Number4:
  override def preSize: Int = 0
end Number4S

// 有前驱
trait Number3T(pre: () => Number4) extends Number3:
  override def size: Int

  override def add: Number3 =
    given Number3          = new Number3T1(() => num4)
    lazy val num4: Number4 = new Number3ST(pre)
    summon[Number3]
  end add
end Number3T
// 不考虑后继特性
class Number3T1(val pre: () => Number4) extends Number3T(pre):
  override def size: Int = pre().preSize + 1
end Number3T1

// 既有前驱又考虑后继特性
class Number3ST(val pre: () => Number4)(using Number3) extends Number3T(pre), Number4:
  override def preSize: Int = pre().preSize + 1
  override def size: Int    = summon[Number3].size - 1
end Number3ST

object Number3:
  def fromInt(n: Int): Number3 = if (n > 0) fromInt(n - 1).add else Number3S
end Number3
