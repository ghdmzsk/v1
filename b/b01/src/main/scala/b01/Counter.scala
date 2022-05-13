package b01

trait Number1 {
  type method1[number2 <: Number2] <: Number3
}
class Number1S[Tail <: Number1] extends Number1 {
  override type method1[number2 <: Number2] = number2#method2[Tail]
}
class Number1T[Tail <: Number1] extends Number1 {
  override type method1[number2 <: Number2] = Number3S[Tail#method1[number2]]
}
class Number1U[Tail <: Number1] extends Number1 {
  override type method1[number2 <: Number2] = Number3S[number2#method2[Tail]]
}

trait Number2 {
  type method2[number1 <: Number1] <: Number3
}
class Number2S[tail <: Number2] extends Number2 {
  override type method2[number1 <: Number1] = number1#method1[tail]
}
class Number2T extends Number2 {
  override type method2[number1 <: Number1] = Number3T
}

trait Number3
class Number3S[Tail <: Number3] extends Number3
class Number3T                  extends Number3

trait Number5 {
  type method1[number6 <: Number6] <: Number6
}
class Number5S[Tail <: Number5] extends Number5 {
  override type method1[number6 <: Number6] = number6#method2[Tail]
}
class Number5T[Tail <: Number5] extends Number5 {
  override type method1[number6 <: Number6] = Number6S[Tail#method1[number6]]
}
class Number5U extends Number5 {
  override type method1[number6 <: Number6] = number6
}

trait Number6 {
  type method2[number5 <: Number5] <: Number6
}
class Number6S[Tail <: Number6] extends Number6 {
  override type method2[number5 <: Number5] = number5#method1[Tail]
}
class Number6T extends Number6 {
  override type method2[number5 <: Number5] = Number6T
}
