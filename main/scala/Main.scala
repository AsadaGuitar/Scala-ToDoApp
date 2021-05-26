object Main{

  def main(args: Array[String]): Unit = {

    println("****************************")
    println("***** Welcome ToDo App *****")
    println("****************************")
    println("\nYour tasks is ... \n")

    Todo.findALl()
    routineMethod()
  }

  def toNum(s: String): Either[String, Int] ={
    try{
      Right(s.toInt)
    }
    catch {
      case e: Exception => Left(e.getMessage)
    }
  }

  def routineMethod(): Unit ={
    println("\n****************************\n")
    println("1: Add Task\n2: Delete Task\n3: Look All\n4: Exit")
    val v = toNum(io.StdIn.readLine)
    (for {
      n <- v
    } yield {
      n match {
        case 1 => addTask()
          routineMethod()
        case 2 => deleteTask()
          routineMethod()
        case 3 => lookAll()
          routineMethod()
        case 4 => exit()
        case _ => println("1 から 4 の数値を入力してください。")
          routineMethod()
      }
    }).left.map {e =>
//      println(e)
      println("1 から 4 の数値を入力してください。")
      routineMethod()
    }
  }

  def addTask(): Unit={
    println("\n****************************\n")
    println("Add Task: ")
    Todo.create(io.StdIn.readLine)
    println("OK.")
  }

  def deleteTask(): Unit ={
    println("\n****************************\n")
    println("Delete Task ID")
    val v: Either[String, Int] = toNum(io.StdIn.readLine)
    (for {
      n <- v
    } yield {
      Todo.delete(n)
      println("OK.")
    }).left.map {e =>
//      println(e)
      println("数値を入力してください。")
    }
  }

  def lookAll(): Unit ={
    println("\n****************************\n")
    println("Your task is ...")
    Todo.findALl()
  }

  def exit(): Unit ={
    println("Bye.")
  }
}