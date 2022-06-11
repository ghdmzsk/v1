package e01

trait Number1:
  def method(num: () => Number1): () => Number1
end Number1

case object Number1S extends Number1:
  override def method(num: () => Number1): () => Number1 = () => throw new Exception
end Number1S

case object Number1T extends Number1:
  override def method(num: () => Number1): () => Number1 = () => Number1Y(() => throw new Exception)
end Number1T

case object Number1U extends Number1:
  override def method(num: () => Number1): () => Number1 = () => Number1S
end Number1U

case object Number1V extends Number1:
  override def method(num: () => Number1): () => Number1 = num
end Number1V

case object Number1W extends Number1:
  override def method(num: () => Number1): () => Number1 = () => Number1Y(() => Number1S)
end Number1W

case object Number1X extends Number1:
  override def method(num: () => Number1): () => Number1 = () => Number1Y(num)
end Number1X

case class Number1Y(tail: () => Number1) extends Number1:
  override def method(num: () => Number1): () => Number1 = tail().method(num)
end Number1Y

case class Number1Z(tail: () => Number1) extends Number1:
  override def method(num: () => Number1): () => Number1 = num().method(tail)
end Number1Z

case class Number1A(tail: () => Number1) extends Number1:
  override def method(num: () => Number1): () => Number1 = () => Number1Y(tail().method(num))
end Number1A

case class Number1B(tail: () => Number1) extends Number1:
  override def method(num: () => Number1): () => Number1 = () => Number1Y(num().method(tail))
end Number1B
