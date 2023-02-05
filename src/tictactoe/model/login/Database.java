package tictactoe.model.login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class Database {
  private static Database instance = new Database();
  private Connection connection;
  private String user = "yassine";
  private String pass = "yassine";

  private Database() {}


  public static Database getInstance() {
    if (instance == null)
      instance = new Database();
    return instance;
  }

  public void connect() throws SQLException {
    // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yassine ", user, pass);
    connection =
        DriverManager.getConnection("jdbc:sqlite:src/tictactoe/model/login/leaderboard.db");
  }

  public void disconnect() throws SQLException {
    connection.close();
  }


  public Connection getConnection() {
    return connection;
  }
}
