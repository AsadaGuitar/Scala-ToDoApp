import Todo.con

import java.sql.{Connection, DriverManager, ResultSet}

trait SqlAccessory {

  val driver: String
  val url: String
  val user: String
  val pass: String
  var con: Connection
  var rs: ResultSet

  def getResultSet(s: String): ResultSet ={
    try{
      setConnection
      val st = con.createStatement
      rs = st.executeQuery(s)
    }
    catch {
      case e: Exception => e.printStackTrace
    }
    rs
  }

  def uploadResultSet(s: String): Unit ={
    try{
      setConnection
      val st = con.createStatement()
      st.executeUpdate(s)
    }
    catch{
      case e: Exception => e.printStackTrace
    }
  }

  private def setConnection: Unit ={
    Class.forName(driver)
    con = DriverManager.getConnection(url, user, pass)
  }
}
