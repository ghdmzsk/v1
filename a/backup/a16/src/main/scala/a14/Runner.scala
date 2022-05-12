package a04

import scala.reflect.runtime.{universe => ru}

trait N1
trait N2
class N3
trait N4 extends N3 with N1
trait N5 extends N2
trait N6 extends N4 with N1
trait N7 extends N3 with N1 with N2 with N4 with N5

object Runner {

  lazy val numAny: Number1 =
    Number1S(
      head1 = () => Number2S(Number2S(Number2S(Number2T, numN1), numN2), numN3),
      head2 = () => Number3T,
      instance = implicitly[ru.TypeTag[Any]]
    )
  lazy val numN1: Number1 = Number1S(head1 = () => Number2S(Number2T, numN7), head2 = () => Number3T, instance = implicitly[ru.TypeTag[N1]])
  lazy val numN2: Number1 =
    Number1S(head1 = () => Number2S(Number2S(Number2T, numN5), numN7), head2 = () => Number3T, instance = implicitly[ru.TypeTag[N2]])
  lazy val numN3: Number1 =
    Number1S(head1 = () => Number2S(Number2S(Number2T, numN4), numN7), head2 = () => Number3T, instance = implicitly[ru.TypeTag[N3]])
  lazy val numN4: Number1 =
    Number1S(
      head1 = () => Number2S(Number2S(Number2T, numN6), numN7),
      head2 = () => Number3S(Number3S(Number3T, numN3), numN1),
      instance = implicitly[ru.TypeTag[N4]]
    )
  lazy val numN5: Number1 =
    Number1S(head1 = () => Number2S(Number2T, numN7), head2 = () => Number3S(Number3T, numN2), instance = implicitly[ru.TypeTag[N5]])
  lazy val numN6: Number1 =
    Number1S(head1 = () => Number2T, head2 = () => Number3S(Number3S(Number3T, numN4), numN1), instance = implicitly[ru.TypeTag[N6]])
  lazy val numN7: Number1 = Number1S(
    head1 = () => Number2T,
    head2 = () => Number3S(Number3S(Number3S(Number3S(Number3S(Number3T, numN3), numN1), numN2), numN4), numN5),
    instance = implicitly[ru.TypeTag[N7]]
  )

  def printlnNumber1(num1: Number1): Unit = num1 match {
    case Number1S(head1, head2, instance) =>
      printlnNumber2(instance, head1())
      printlnNumber3(instance, head2())
  }

  def printlnNumber1_3(num1: Number1): Unit = num1 match {
    case Number1S(_, head2, instance) =>
      printlnNumber3(instance, head2())
  }

  def printlnNumber2(instance: ru.TypeTag[_], num2: Number2): Unit = num2 match {
    case Number2S(tail, head @ Number1S(_, _, instance2)) =>
      println("v1", instance, instance2, instance2.tpe <:< instance.tpe)
      assert(instance2.tpe <:< instance.tpe)

      printlnNumber1(head)
      printlnNumber2(instance, tail)
    case Number2T =>
  }

  def printlnNumber3(instance: ru.TypeTag[_], num3: Number3): Unit = num3 match {
    case Number3S(tail, head @ Number1S(_, _, instance2)) =>
      println("v2", instance, instance2, instance.tpe <:< instance2.tpe)
      assert(instance2.tpe <:< instance2.tpe)

      printlnNumber1_3(head)
      printlnNumber3(instance, tail)
    case Number3T =>
  }

  def main(arr: Array[String]): Unit = {
    printlnNumber1(numAny)
  }

//(v1,TypeTag[Any],TypeTag[a12.N3],true)
//(v1,TypeTag[a12.N3],TypeTag[a12.N7],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N5],true)
//(v2,TypeTag[a12.N5],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N4],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N3],true)
//(v1,TypeTag[a12.N3],TypeTag[a12.N4],true)
//(v1,TypeTag[a12.N4],TypeTag[a12.N7],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N5],true)
//(v2,TypeTag[a12.N5],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N4],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N3],true)
//(v1,TypeTag[a12.N4],TypeTag[a12.N6],true)
//(v2,TypeTag[a12.N6],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N6],TypeTag[a12.N4],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v1,TypeTag[Any],TypeTag[a12.N2],true)
//(v1,TypeTag[a12.N2],TypeTag[a12.N7],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N5],true)
//(v2,TypeTag[a12.N5],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N4],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N3],true)
//(v1,TypeTag[a12.N2],TypeTag[a12.N5],true)
//(v1,TypeTag[a12.N5],TypeTag[a12.N7],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N5],true)
//(v2,TypeTag[a12.N5],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N4],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N5],TypeTag[a12.N2],true)
//(v1,TypeTag[Any],TypeTag[a12.N1],true)
//(v1,TypeTag[a12.N1],TypeTag[a12.N7],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N5],true)
//(v2,TypeTag[a12.N5],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N4],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N4],TypeTag[a12.N3],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N2],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N1],true)
//(v2,TypeTag[a12.N7],TypeTag[a12.N3],true)

}
