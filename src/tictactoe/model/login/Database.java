package tictactoe.model.login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  public static Database instance = new Database();
  private Connection connection;

  private Database() {}


  public static Database getInstance() {
    return instance;
  }

  public void connect() throws SQLException {
    connection =
        DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
  }

  public void disconnect() throws SQLException {
    connection.close();
  }


  public Connection getConnection() {
    return connection;
  }
}
