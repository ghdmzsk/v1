package a01

object Runner {

  def count[T <: Number1](implicit i: Length[T]): Int = i.length

  def main(arr: Array[String]): Unit = {
    {
      type Number1 = Number1S[Number1S[Number1S[Number1T]]]
      val count1  = count[Number1]
      val result1 = 3
      assert(count1 == result1)
    }
    {
      type Number1 = Number1S[Number1S[Number1T]]
      val count1  = count[Number1]
      val result1 = 2
      assert(count1 == result1)
    }
    {
      type Number1 = Number1S[Number1T]
      val count1  = count[Number1]
      val result1 = 1
      assert(count1 == result1)
    }
    {
      val count1  = count[Number1T]
      val result1 = 0
      assert(count1 == result1)
    }
  }

}
