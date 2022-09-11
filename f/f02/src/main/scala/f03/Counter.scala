package f03

trait Number3
trait Number3S(val tail: Number3) extends Number3

// ===== 1
package impl1:
  case class Number3T(override val tail: Number3, data: Any) extends Number3S(tail)

  object Number3T:
    def from(list: List[Any]): Number3 = list.match
      case head :: tail => Number3T(from(tail), head)
      case _            => new Number3 {}
    end from
  end Number3T
end impl1

// ===== 2
package impl2:
  case class Number3U(data: Any) extends Number3

  object Number3U:
    def from(data: Any, tag: Int): Number3 =
      if (tag > 0) new Number3S(from(data, tag - 1)) {}
      else Number3U(data)
    end from
  end Number3U
end impl2
