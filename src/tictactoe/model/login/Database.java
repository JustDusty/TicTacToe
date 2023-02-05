package tictactoe.model.login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import tictactoe.application.Util;

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
    // connection = DriverManager.getConnection("jdbc:sqlite:leaderboard.db");
    Util.LOGGER.log(Level.INFO, "Connected to MySQL Database");
  }

  public void disconnect() throws SQLException {
    connection.close();
  }


  public Connection getConnection() {
    return connection;
  }
}
