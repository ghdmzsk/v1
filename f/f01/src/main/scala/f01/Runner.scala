package f01

object Runner {

  def main(arr: Array[String]): Unit =
    locally {
      for (i <- 0 to 100)
        val num1 = Number3.fromInt(i)
        assert(num1.size == i)

        var currentImpl: Option[Any] = Option(num1)
        var i1                       = i

        while (currentImpl.isDefined) {
          val current = currentImpl.get

          val size1 = current.match
            case s: Number3 => Option(s.size)
            case _          => Option.empty

          val size2 = current.match
            case s: Number4 => Option(s.preSize)
            case _          => Option.empty

          val (s1, s2) = (size1, size2).match
            case (Some(i1), Some(i2)) => (i1, i2)
            case (Some(i1), None)     => (i1, i1)
            case (None, Some(i2))     => (i2, i2)

          assert(s1 == i1)
          assert(s2 == i1)

          // Number3T 代表有前驱
          val confirm1 = current.match
            case _: Number3T => true
            case _           => false

          if (i1 > 0) assert(confirm1) else assert(!confirm1)

          // Number3S 代表有后继
          val confirm2 = current.match
            case _: Number4 => true
            case _          => false

          if (i1 < i) assert(confirm2) else assert(!confirm2)

          // 当前 Number 表示 0 时的特征
          val isZero = current.match
            case Number3S if i == 0    => true
            case _: Number4S if i != 0 => true
            case _                     => false

          if (i1 == 0) assert(isZero) else assert(!isZero)

          current.match
            case s: Number3T1 =>
              currentImpl = Option(s.pre)
              i1 -= 1
            case s: Number3ST =>
              currentImpl = Option(s.pre)
              i1 -= 1
            case _ =>
              currentImpl = Option.empty

        }
        assert(i1 == 0)
    }
  end main

}
