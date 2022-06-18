object Test {
  val test = 42
}

class Test2 {
  companion object HelloWorld {
    fun printHelloWorld() {
      println("hello world!")
    }
  }

  object FooBar {
    fun printFromXToY(x: Int, y: Int){
      for (number in x..y){
        println(number)
      }
    }
    object Inner {
      val answer = 42
    }
  }
}

fun main(){
  Main.main(null)
}
