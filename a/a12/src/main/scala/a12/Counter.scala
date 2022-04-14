package a12

import scala.reflect.runtime.{universe => ru}

trait Number1
case class Number1S(head1: () => Number2, head2: () => Number3, instance: ru.TypeTag[_]) extends Number1

trait Number2
case class Number2S(tail: Number2, head: Number1) extends Number2
case object Number2T                              extends Number2

trait Number3
case class Number3S(tail: Number3, head: Number1) extends Number3
case object Number3T                              extends Number3
