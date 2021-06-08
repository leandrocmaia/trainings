object FunctionalProgramming extends App {
  
  case class Person(name: String) {
    def apply(age: Int): Unit = println(s"I have aged $age years")  
  }

  val bob = new Person("Bob")
  bob(30)

  assert(bob match {
    case Person(x) => true
    case _ => false
  })

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  assert(simpleIncrementer(2) == 3)

  val stringConcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = s"$v1 $v2"
  }

  assert(stringConcatenator("Bob", "Fisher") == "Bob Fisher")

  val doubler: Int => Int = (x: Int) => x * 2

  assert(doubler(100) == 200)

  val aMappedList = List(1, 2, 3).map(x => x * 2)
  
  assert(aMappedList == List(2, 4, 6))

  val aFlatMappedList = List(1, 2, 3).flatMap(x => List(x, x * 2))
  
  assert(aFlatMappedList == List(1, 2, 2, 4, 3, 6))

  val aFilteredList = List(1, 2, 3).filter(_ <=2 )
  assert(aFilteredList == List(1, 2))

  val allPairs = List(1, 2, 3).flatMap(number => List("a", "b", "c").map(letter => s"$number$letter"))
  assert(allPairs == List("1a", "1b", "1c", "2a", "2b", "2c", "3a", "3b", "3c"))

  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List("a", "b", "c")
  } yield s"$number$letter"

  assert(alternativePairs == List("1a", "1b", "1c", "2a", "2b", "2c", "3a", "3b", "3c"))

  /**
    * Colletions
    */

  // lists

  val aList = List(1, 2, 3)
  val firstEl = aList.head
  val rest = aList.tail
  val appendedList = 0 :: aList
  val extendedList = 0 +: aList :+ 4

  assert(extendedList == List(0, 1, 2, 3, 4))

  val aSequence: Seq[Int] = Seq(1, 2, 3) // Seq.apply(1, 2, 3)
  val aSeqEl: Int = aSequence(1) // at index 1: 2
  
  assert(aSeqEl == 2)


  // vectors: fast seq impl
  val aVector = Vector(1, 2, 3)

  // set: no duplicates
  val aSet = Set(1, 1, 2 , 3, 3, 4)

  assert(aSet == Set(1, 2, 3, 4))
  
  def contains = (set: Set[Int], index: Int) => set.contains(index)
  
  assert(contains(aSet, 5) == false)

  val extendedSet = aSet + 5 - 1

  assert(extendedSet == Set(2, 3, 4, 5))
  assert(contains(extendedSet, 5) == true)
  assert(contains(extendedSet, 1) == false)

  // ranges
  val aRange = 1 to 5
  val twoByTwo = aRange.map(_ * 2).toList

  assert(twoByTwo == List(2, 4, 6, 8, 10))

  // tuples
  val aTuple = ("Guns n Roses", "Rock", 1982)

  assert(aTuple._1 == "Guns n Roses")
  assert(aTuple._2 == "Rock")
  assert(aTuple._3 == 1982)

  // Maps
  val aPhonebook: Map[String, Int] = Map(
    ("Bob", 1),
    ("Alice", 2),
    "Steven" -> 3
  )

  assert(aPhonebook.size == 3)
}
