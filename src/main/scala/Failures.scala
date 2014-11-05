import records._

object Failures {
  val x = Rec("1" -> 1)
  val y = Rec("1" -> 1, "2" -> 2)
  List(x, y).map(_.`1`) // works: infers to List[Rec{def `1`: Int}]
  List(Rec("1" -> 1), Rec("1" -> 1)) // works
  // List(Rec("1" -> 1),Rec("1" -> 1, "2" -> 2)).map(_.`1`) // does not work: infers List[Rec]

  (if (true) Rec("1" -> 1) else Rec("1" -> 1)).`1` // works
  (if (true) x else y).`1` // works
  // (if (true) Rec("1" -> 1) else Rec("1" -> 1, "2" -> 2)).`1` // does not work

  import reflect.runtime.universe._
  def get[T: TypeTag](v: T) = ???
  get(x) // works
  // get(Rec("1" -> 1)) // does not work: No TypeTag available for Workaround
}
