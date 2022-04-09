package a03

object Runner {

  class PlusImpl[T1 <: Number1, T2 <: Number2] {
    def apply[T3 <: Number1]()(implicit i: Plus[T1, T2, T3], len: Length[T3]): Int = len.length
  }
  def plus[T1 <: Number1, T2 <: Number2]: PlusImpl[T1, T2] = new PlusImpl

  class MinusImpl[T1 <: Number1, T2 <: Number2] {
    def apply[T3 <: Number1]()(implicit i: Minus[T1, T2, T3], len: Length[T3]): Int = len.length
  }
  def minus[T1 <: Number1, T2 <: Number2]: MinusImpl[T1, T2] = new MinusImpl

  def main(arr: Array[String]): Unit = {
    {
      val count1  = plus[Number1T, Number2T]()
      val count2  = minus[Number1T, Number2T]()
      val result1 = 0 + 0
      val result2 = 0 - 0
      assert(count1 == result1)
      assert(count2 == result2)
    }
    {
      type Num1 = Number1S[Number1T]
      val count1  = plus[Num1, Number2T]()
      val count2  = minus[Num1, Number2T]()
      val result1 = 1 + 0
      val result2 = 1 - 0
      assert(count1 == result1)
      assert(count2 == result2)
    }
    {
      type Num1 = Number1S[Number1S[Number1T]]
      val count1  = plus[Num1, Number2T]()
      val count2  = minus[Num1, Number2T]()
      val result1 = 2 + 0
      val result2 = 2 - 0
      assert(count1 == result1)
      assert(count2 == result2)
    }

    {
      type Num2 = Number2S[Number2T]
      val count1  = plus[Number1T, Num2]()
      val count2  = minus[Number1T, Num2]()
      val result1 = 0 + 1
      val result2 = if (0 - 1 > 0) 0 - 1 else 0
      assert(count1 == result1)
      assert(count2 == result2)
    }
    {
      type Num1 = Number1S[Number1T]
      type Num2 = Number2S[Number2T]
      val count1  = plus[Num1, Num2]()
      val count2  = minus[Num1, Num2]()
      val result1 = 1 + 1
      val result2 = 1 - 1
      assert(count1 == result1)
      assert(count2 == result2)
    }
    {
      type Num1 = Number1S[Number1S[Number1T]]
      type Num2 = Number2S[Number2T]
      val count1  = plus[Num1, Num2]()
      val count2  = minus[Num1, Num2]()
      val result1 = 2 + 1
      val result2 = 2 - 1
      assert(count1 == result1)
      assert(count2 == result2)
    }

    {
      type Num2 = Number2S[Number2S[Number2T]]
      val count1  = plus[Number1T, Num2]()
      val count2  = minus[Number1T, Num2]()
      val result1 = 0 + 2
      val result2 = if (0 - 2 > 0) 0 - 2 else 0
      assert(count1 == result1)
      assert(count2 == result2)
    }
    {
      type Num1 = Number1S[Number1T]
      type Num2 = Number2S[Number2S[Number2T]]
      val count1  = plus[Num1, Num2]()
      val count2  = minus[Num1, Num2]()
      val result1 = 1 + 2
      val result2 = if (1 - 2 > 0) 1 - 2 else 0
      assert(count1 == result1)
      assert(count2 == result2)
    }
    {
      type Num1 = Number1S[Number1S[Number1T]]
      type Num2 = Number2S[Number2S[Number2T]]
      val count1  = plus[Num1, Num2]()
      val count2  = minus[Num1, Num2]()
      val result1 = 2 + 2
      val result2 = 2 - 2
      assert(count1 == result1)
      assert(count2 == result2)
    }
  }

}
