package tictactoe.model.login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class Database {
  public static Database instance = new Database();
  private Connection connection;
  private String user = "yassine";
  private String pass = "yassine";

  private Database() {}


  /**
   * Singleton class that corresponds to the SQL database for handling user data
   * 
   * @return the instance of the database
   */
  public static Database getInstance() {
    if (instance == null)
      instance = new Database();
    return instance;
  }

  /**
   * Initializes the connection to the local MySQL database
   * 
   * @throws SQLException
   */
  public void connect() throws SQLException {
    connection = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
    // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yassine", user, pass);
  }

  /**
   * Closes the JDBC connection
   * 
   * @throws SQLException
   */
  public void disconnect() throws SQLException {
    connection.close();
  }


  public Connection getConnection() {
    return connection;
  }
}
