package f01

object Runner {

  def main(arr: Array[String]): Unit = {
    {
      for (i <- 0 to 100) {
        val num1 = Number3.fromInt(i)
        assert(num1.size == i)

        var current: Number3 = num1
        var i1               = i

        while (current != null) {
          // Number3T 代表有前驱
          val confirm1 = current match {
            case _: Number3T => true
            case _           => false
          }
          if (i1 > 0) assert(confirm1)
          else assert(!confirm1)

          // Number3S 代表有后继
          val confirm2 = current match {
            case _: Number3S => true
            case _           => false
          }
          if (i1 < i) assert(confirm2)
          else assert(!confirm2)

          // 当前 Number 表示 0 时的特征
          val isZero = current match {
            case Number3U if i == 0     => true
            case _: Number3S1 if i != 0 => true
            case _                      => false
          }
          if (i1 == 0) assert(isZero)
          else assert(!isZero)

          current match {
            case s: Number3T => current = s.pre
            case _           => current = null
          }
          i1 -= 1
        }
      }
    }
  }

}
