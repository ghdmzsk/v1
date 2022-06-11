package e01

object NumberGen:

  def numberGen1(number: () => Number1, n: Int): () => Number1 = () => Number1S
  def numberGen2(number: () => Number1, n: Int): () => Number1 = () => Number1T
  def numberGen3(number: () => Number1, n: Int): () => Number1 = () => Number1U
  def numberGen4(number: () => Number1, n: Int): () => Number1 = () => Number1V
  def numberGen5(number: () => Number1, n: Int): () => Number1 = () => Number1W
  def numberGen6(number: () => Number1, n: Int): () => Number1 = () => Number1X

  def numberGen7(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1Y(numberGen7(number, n - 1)) else number
  def numberGen8(number: () => Number1, n: Int): () => Number1 = () => Number1Y(number)
  def numberGen9(number: () => Number1, n: Int): () => Number1 =
    lazy val n: () => Number1 = () => Number1Y(n)
    n
  end numberGen9

  def numberGen10(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1Z(numberGen10(number, n - 1)) else number
  def numberGen11(number: () => Number1, n: Int): () => Number1 = () => Number1Z(number)
  def numberGen12(number: () => Number1, n: Int): () => Number1 =
    lazy val n: () => Number1 = () => Number1Z(n)
    n
  end numberGen12

  def numberGen13(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1A(numberGen13(number, n - 1)) else number
  def numberGen14(number: () => Number1, n: Int): () => Number1 = () => Number1A(number)
  def numberGen15(number: () => Number1, n: Int): () => Number1 =
    lazy val n: () => Number1 = () => Number1A(n)
    n
  end numberGen15

  def numberGen16(number: () => Number1, n: Int): () => Number1 = if (n > 0) () => Number1B(numberGen16(number, n - 1)) else number
  def numberGen17(number: () => Number1, n: Int): () => Number1 = () => Number1B(number)
  def numberGen18(number: () => Number1, n: Int): () => Number1 =
    lazy val n: () => Number1 = () => Number1B(n)
    n
  end numberGen18

  def numberGen19(number: () => Number1, n: Int): () => Number1 = () => throw new Exception

  val partGen: List[(Char, (() => Number1, Int) => () => Number1)] = List(
    ('a', numberGen1),
    ('b', numberGen2),
    ('c', numberGen3),
    ('d', numberGen4),
    ('e', numberGen5),
    ('f', numberGen6),
    ('g', numberGen7),
    ('h', numberGen8),
    ('i', numberGen9),
    ('j', numberGen10),
    ('k', numberGen11),
    ('l', numberGen12),
    ('m', numberGen13),
    ('n', numberGen14),
    ('o', numberGen15),
    ('p', numberGen16),
    ('q', numberGen17),
    ('r', numberGen18),
    ('s', numberGen19)
  )

  val parGenMap: Map[Char, (() => Number1, Int) => () => Number1] = partGen.to(Map)

  def genNumber(a1: Char, a2: Char, value: Int): () => Number1 =
    val gen1                     = parGenMap(a1)
    val gen2                     = parGenMap(a2)
    lazy val num1: () => Number1 = gen1(num2, value)
    lazy val num2: () => Number1 = gen2(num1, value)
    num1
  end genNumber

  def count(num: () => Number1): Int =
    val next =
      try Option(num())
      catch case _: Throwable => Option.empty

    next.match
      case Some(Number1Y(tail)) => count(tail) + 1
      case Some(Number1S)       => 0
      case None                 => 0
  end count

end NumberGen
