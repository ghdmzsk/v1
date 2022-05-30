package a04

trait Number1 {
  var next: Number2
  def method1(num4: Number4): Unit
}
case class Number1S(override var next: Number2) extends Number1 {
  override def method1(num4: Number4): Unit = {
    val num = Number3S(null, null)
    next.pre = num
    // this.next = num
    // num.next = next
    // num.pre = this
    num4.method3(num)
    num4.method4(num)
  }
}

trait Number2 {
  var pre: Number1
  def method2(num4: Number4): Unit
}
case class Number2S(override var pre: Number1) extends Number2 {
  override def method2(num4: Number4): Unit = {
    val num = Number3S(null, null)
    pre.next = num
    num.pre = pre
  }
}

case class Number3S(override var pre: Number1, override var next: Number2) extends Number1 with Number2 {
  override def method1(num4: Number4): Unit = {}
  override def method2(num4: Number4): Unit = {
    val num = Number3S(null, null)
    pre.next = num
    num.pre = pre
  }
}

trait Number4 {
  def method3(num1: Number1): Unit
  def method4(num2: Number2): Unit
}
case class Number4S(tail: Number4) extends Number4 {
  var preTemp: Number1 = null
  override def method3(num1: Number1): Unit = {
    preTemp = num1
  }
  override def method4(num2: Number2): Unit = {

  }
}
case object Number4T extends Number4 {
  override def method3(num1: Number1): Unit = {}
  override def method4(num2: Number2): Unit = {}
}
