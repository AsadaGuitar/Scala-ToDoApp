import java.sql.{Connection, ResultSet}

object Todo extends SqlAccessory {

  override val driver: String = "com.mysql.cj.jdbc.Driver"
  override val url: String = "jdbc:mysql://localhost:3306/scala"
  override val user: String = "root"
  override val pass: String = "*******"
  override var con: Connection = _
  override var rs: ResultSet = _

  def findALl(): Unit ={
    val sql = "select id, todo from todo"
    rs = getResultSet(sql)
    while (rs.next()){
      println("ID %d: %s".format(
        rs.getInt("id"), rs.getString("todo")))
    }
  }

  def create(s: String): Unit ={
    val sql = "insert into todo(todo) values('%s');".format(s)
    uploadResultSet(sql)
  }

  def delete(d: Int): Unit ={
    val sql = "delete from todo where id = '%d'".format(d)
    uploadResultSet(sql)
  }
}
