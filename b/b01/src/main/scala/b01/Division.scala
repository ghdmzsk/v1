package b01

object Division {

  class Tag[T]
  object Tag {
    def apply[T]: Tag[T] = new Tag[T]
  }
  def i[T](tag1: Tag[T], tag2: Tag[T]): List[Tag[T]] = List(tag1, tag2)

  class Num1     extends Number1S[Number1S[Number1S[Num1Zero]]]
  class Num1Zero extends Number1T[Num1]
  type Num2 =
    Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2T]]]]]]]]]]]]
  type Num3 = Number3S[Number3S[Number3S[Number3S[Number3T]]]]

  i(Tag[Num1#method1[Num2]], Tag[Num3])
  i(Tag[Num2#method2[Num1Zero]], Tag[Num3])

  class Num4     extends Number1S[Number1S[Number1S[Number1S[Num4Zero]]]]
  class Num4Zero extends Number1T[Num4]
  type Num5 = Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2S[Number2T]]]]]]]]]]
  type Num6 = Number3S[Number3S[Number3S[Number3T]]]
  type Num7 = Number3S[Number3S[Number3T]]

  i(Tag[Num4#method1[Num5]], Tag[Num7])
  i(Tag[Num5#method2[Num4Zero]], Tag[Num6])

}
