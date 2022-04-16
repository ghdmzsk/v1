package a10

import scala.util.Random

object Runner {

  def count(num1: Number1): Int = num1 match {
    case Number1_7(tail1, tail2, tail3, tail4, tail5, tail6, tail7) =>
      count(tail1) + count(tail2) + count(tail3) + count(tail4) + count(tail5) + count(tail6) + count(tail7) + 1
    case Number1_6(tail1, tail2, tail3, tail4, tail5, tail6) =>
      count(tail1) + count(tail2) + count(tail3) + count(tail4) + count(tail5) + count(tail6) + 1
    case Number1_5(tail1, tail2, tail3, tail4, tail5) => count(tail1) + count(tail2) + count(tail3) + count(tail4) + count(tail5) + 1
    case Number1_4(tail1, tail2, tail3, tail4)        => count(tail1) + count(tail2) + count(tail3) + count(tail4) + 1
    case Number1_3(tail1, tail2, tail3)               => count(tail1) + count(tail2) + count(tail3) + 1
    case Number1_2(tail1, tail2)                      => count(tail1) + count(tail2) + 1
    case Number1_1(tail1)                             => count(tail1) + 1
    case Number1_0                                    => 0
  }

  def genNumber1(n: Int): Number1 = {
    if (n == 0) Number1_0
    else
      math.abs(Random.nextInt()) % 7 match {
        case 0 => genNumber1_1(n)
        case 1 => genNumber1_2(n)
        case 2 => genNumber1_3(n)
        case 3 => genNumber1_4(n)
        case 4 => genNumber1_5(n)
        case 5 => genNumber1_6(n)
        case 6 => genNumber1_7(n)
      }
  }

  def genNumber1_1(n: Int): Number1 = {
    Number1_1(genNumber1(n - 1))
  }

  def genNumber1_2(n: Int): Number1 = {
    Number1_2(genNumber1(n - 1), genNumber1(n - 1))
  }

  def genNumber1_3(n: Int): Number1 = {
    Number1_3(genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1))
  }

  def genNumber1_4(n: Int): Number1 = {
    Number1_4(genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1))
  }

  def genNumber1_5(n: Int): Number1 = {
    Number1_5(genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1))
  }

  def genNumber1_6(n: Int): Number1 = {
    Number1_6(genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1), genNumber1(n - 1))
  }

  def genNumber1_7(n: Int): Number1 = {
    Number1_7(
      genNumber1(n - 1),
      genNumber1(n - 1),
      genNumber1(n - 1),
      genNumber1(n - 1),
      genNumber1(n - 1),
      genNumber1(n - 1),
      genNumber1(n - 1)
    )
  }

  def main(arr: Array[String]): Unit = {
    for {
      i1 <- 1 to 10
      i2 <- 1 to 10
    } yield {
      val num1   = genNumber1(i1)
      val num2   = genNumber1(i2)
      val count1 = count(num1)
      val count2 = count(num2)
      val count3 = count(num1.method1(num2))
      val count4 = count(num2.method1(num1))
      assert(count3 == count1 + count2)
      assert(count4 == count1 + count2)
      println(count1, count2, count3)
    }
  }

//(1,1,2)
//(1,7,8)
//(1,33,34)
//(1,80,81)
//(1,401,402)
//(1,484,485)
//(1,8514,8515)
//(1,4136,4137)
//(1,3494,3495)
//(1,660661,660662)
//(4,1,5)
//(7,7,14)
//(4,9,13)
//(4,108,112)
//(6,135,141)
//(6,1012,1018)
//(8,4536,4544)
//(2,22194,22196)
//(5,148281,148286)
//(2,582699,582701)
//(6,1,7)
//(18,5,23)
//(32,11,43)
//(22,93,115)
//(27,328,355)
//(7,203,210)
//(5,6367,6372)
//(13,20075,20088)
//(3,54803,54806)
//(40,492418,492458)
//(91,1,92)
//(133,4,137)
//(129,41,170)
//(25,23,48)
//(110,399,509)
//(19,2670,2689)
//(85,10793,10878)
//(144,37565,37709)
//(23,99240,99263)
//(118,121825,121943)
//(588,1,589)
//(285,6,291)
//(305,12,317)
//(490,153,643)
//(106,630,736)
//(87,677,764)
//(439,5512,5951)
//(366,31729,32095)
//(276,29734,30010)
//(363,85589,85952)
//(1942,1,1943)
//(1249,6,1255)
//(1867,23,1890)
//(1814,63,1877)
//(2006,270,2276)
//(1809,1926,3735)
//(133,4693,4826)
//(1191,10992,12183)
//(2810,107103,109913)
//(570,130294,130864)
//(11538,1,11539)
//(4730,4,4734)
//(9158,24,9182)
//(957,40,997)
//(2386,495,2881)
//(10565,1401,11966)
//(7795,5329,13124)
//(2914,33834,36748)
//(11092,30083,41175)
//(3638,593209,596847)
//(37011,1,37012)
//(25509,7,25516)
//(29049,28,29077)
//(7498,79,7577)
//(10223,593,10816)
//(23630,418,24048)
//(11884,9902,21786)
//(9717,2138,11855)
//(31983,103343,135326)
//(12130,229223,241353)
//(41575,1,41576)
//(2724,4,2728)
//(116070,27,116097)
//(68696,74,68770)
//(132786,706,133492)
//(136285,1781,138066)
//(29772,4236,34008)
//(49984,19313,69297)
//(102025,90425,192450)
//(29130,150941,180071)
//(213943,1,213944)
//(583698,4,583702)
//(477170,11,477181)
//(317613,130,317743)
//(206069,295,206364)
//(560264,1808,562072)
//(352683,11790,364473)
//(539006,4237,543243)
//(253442,127171,380613)
//(181977,476517,658494)

}
