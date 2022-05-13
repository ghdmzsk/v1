package b01

object Plus {

  class number1ZeroU extends Number1U[number1ZeroU]

  class Tag[T]
  object Tag {
    def apply[T]: Tag[T] = new Tag[T]
  }
  def i[T](tag1: Tag[T], tag2: Tag[T]): List[Tag[T]] = List(tag1, tag2)

  type Num1 = Number1T[Number1T[Number1T[number1ZeroU]]]
  type Num2 = Number2S[Number2S[Number2S[Number2S[Number2T]]]]
  type Num3 = Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3S[Number3T]]]]]]]

  type Num4 = Number5T[Number5T[Number5U]]
  type Num5 = Number6S[Number6S[Number6S[Number6S[Number6T]]]]
  type Num6 = Number6S[Number6S[Number6S[Number6S[Number6S[Number6S[Number6T]]]]]]

  i(Tag[Num2#method2[Num1]], Tag[Num3])
  i(Tag[Num4#method1[Num5]], Tag[Num6])

}
