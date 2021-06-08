import scala.math._

object Main extends App {
  class Animal {
    val age = 0
    def eat() = println("I am eating")
  }

  val aAnimal = new Animal

  class Dog(val name: String) extends Animal

  val aDog = new Dog("Lassie")
  println(aDog.name)

  val aDeclaredAnimal = new Dog("Bob")
  aDeclaredAnimal.eat()

  abstract class WalkingAnimal {
    val hasLegs = true
    def walk(): Unit
  }

  val aWalkingAnimal = new WalkingAnimal {
    override def walk(): Unit = println("I am walking")
  }

  aWalkingAnimal.walk

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit
  }

  class Crocodille extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating an animal")
    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }


  val aCrocodile = new Crocodille
  aCrocodile.eat(new Animal)
  aCrocodile ?! "Is death the end?"

  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur and can eat anything")
  }

  dinosaur.eat(new Animal)

  object MySingleton {
    val value = 1
    def method(): Int = 10
    def apply(x: Int): Int = x * 10 
  }

  println(MySingleton.method())
  println(MySingleton(5))

  // companion
  object Animal {
    val canLiveForever = false
  }

  println(Animal.canLiveForever)

  case class Circle(radius: Double) {
    import Circle._
    def area: Double = calculateArea(radius)
  }

  object Circle {
    private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
  }

  val circle1 = Circle(5.0)

  println(circle1.area)


  
  /* case class 
  - equals and hashcode
  - companion object with apply
  - pattern matchin
  */

  case class Person(name: String, age: Int)  

  val bob = Person("Bob", 30) 

  // exceptions
  try {
    val x: String = null
    x.length
  } catch {
    case e: Exception => println(s"Error: ${e.getMessage()}")
  } finally {
    println("Finally")
  }


  // generics
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): MyList[B]

    def printElements: String
    override def toString: String = "[" + printElements + "]"

   }

   case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyList[B] = Cons(element, this)
    def printElements: String =
      if(t.isEmpty) "" + h
      else h + ", " + t.printElements
  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
    override def printElements: String =  ""
  }

  val aListofInts = Cons(1, Cons(2, Empty))
  println(aListofInts)
  val aListOfStrings = Cons("A", Cons("B", Cons("C", Empty)))
  println(aListOfStrings)
} 