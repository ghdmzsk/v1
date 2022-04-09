package a02

object Runner {

  class PlusImpl[T1 <: Number1, T2 <: Number2] {
    def apply[T3 <: Number1]()(implicit i: Plus[T1, T2, T3], len: Length[T3]): Int = len.length
  }

  def plus[T1 <: Number1, T2 <: Number2] = new PlusImpl[T1, T2]

  def main(arr: Array[String]): Unit = {
    {
      val count1  = plus[Number1T, Number2T]()
      val result1 = 0 + 0
      assert(count1 == result1)
    }
    {
      type Num1 = Number1S[Number1T]
      val count1  = plus[Num1, Number2T]()
      val result1 = 1 + 0
      assert(count1 == result1)
    }
    {
      type Num1 = Number1S[Number1S[Number1T]]
      val count1  = plus[Num1, Number2T]()
      val result1 = 2 + 0
      assert(count1 == result1)
    }

    {
      type Num2 = Number2S[Number2T]
      val count1  = plus[Number1T, Num2]()
      val result1 = 0 + 1
      assert(count1 == result1)
    }
    {
      type Num1 = Number1S[Number1T]
      type Num2 = Number2S[Number2T]
      val count1  = plus[Num1, Num2]()
      val result1 = 1 + 1
      assert(count1 == result1)
    }
    {
      type Num1 = Number1S[Number1S[Number1T]]
      type Num2 = Number2S[Number2T]
      val count1  = plus[Num1, Num2]()
      val result1 = 2 + 1
      assert(count1 == result1)
    }

    {
      type Num2 = Number2S[Number2S[Number2T]]
      val count1  = plus[Number1T, Num2]()
      val result1 = 0 + 2
      assert(count1 == result1)
    }
    {
      type Num1 = Number1S[Number1T]
      type Num2 = Number2S[Number2S[Number2T]]
      val count1  = plus[Num1, Num2]()
      val result1 = 1 + 2
      assert(count1 == result1)
    }
    {
      type Num1 = Number1S[Number1S[Number1T]]
      type Num2 = Number2S[Number2S[Number2T]]
      val count1  = plus[Num1, Num2]()
      val result1 = 2 + 2
      assert(count1 == result1)
    }
  }

}
