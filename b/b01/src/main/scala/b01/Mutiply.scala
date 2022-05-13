package b01

object Mutiply {

  class Tag[T]
  object Tag {
    def apply[T]: Tag[T] = new Tag[T]
  }
  def i[T](tag1: Tag[T], tag2: Tag[T]): List[Tag[T]] = List(tag1, tag2)

  class Num1     extends Number1T[Number1T[Number1T[Num1Zero]]]
  class Num1Zero extends Number1S[Num1]
  type Num2 = Number2S[Number2S[Number2S[Number2S[Number2T]]]]
  type Num3 =
    Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3T]]]]]]]]]]]]

  i(Tag[Num2#method2[Num1]], Tag[Num3])
  i(Tag[Num1Zero#method1[Num2]], Tag[Num3])

}
