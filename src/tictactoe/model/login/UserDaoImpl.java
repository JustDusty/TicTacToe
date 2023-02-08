package tictactoe.model.login;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * Implementation of the Data Access Object for the handling of user data
 *
 */
public class UserDaoImpl implements UserDao {

  public void createTable() {

  }

  @Override
  public void delete(User u) {
    var connection = Database.getInstance().getConnection();
    try {
      var statement = connection.prepareStatement("DELETE FROM users WHERE userid=?");
      statement.setInt(1, u.getID());
      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  @Override
  public List<User> getAll() {
    List<User> users = new ArrayList<>();
    var connection = Database.getInstance().getConnection();
    try {
      var statement = connection.createStatement();
      var resultSet = statement.executeQuery("SELECT userid,username,wins,losses FROM users");

      while (resultSet.next()) {
        var userid = resultSet.getInt(1);
        var username = resultSet.getString(2);
        var wins = resultSet.getInt(3);
        var losses = resultSet.getInt(4);
        users.add(new User(userid, username, wins, losses));
      }
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

  @Override
  public Optional<User> getByID(int id) {
    var connection = Database.getInstance().getConnection();
    try {
      var statement = connection.prepareStatement("select userid,username from users where id=?;");
      statement.setInt(1, id);
      var resultSet = statement.executeQuery();
      if (resultSet.next()) {
        var userid = resultSet.getInt(1);
        var username = resultSet.getString(2);
        User user = new User(userid, username);
        return Optional.of(user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @SuppressWarnings("unused")
  @Override
  public void save(User u) {
    var connection = Database.getInstance().getConnection();
    try {
      var sqlite = "INSERT INTO users (userid, username,wins,losses,score)" + "VALUES (?,?,?,?,?)"
          + "ON CONFLICT(userid) DO UPDATE SET "
          + "wins = excluded.wins, losses = excluded.losses, score = excluded.score;";
      var mysql = "INSERT INTO users (userid, username, wins, losses,score) "
          + "VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE "
          + "username = VALUES(username), wins =VALUES(wins), losses = VALUES(losses),score = VALUES(score);";

      var statement = connection.prepareStatement(sqlite);

      statement.setInt(1, u.getID());
      statement.setString(2, u.getName());
      statement.setInt(3, u.getWins());
      statement.setInt(4, u.getLosses());
      statement.setDouble(5, u.getScore());


      statement.executeUpdate();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates the rank of the user based on the score in the SQL Database
   * 
   * @param user : the user
   */
  @Override
  public void update(User user) {
    var connection = Database.getInstance().getConnection();
    int rank = 0;
    try {
      var sql =
          "SELECT row_num FROM (SELECT row_number() OVER (ORDER BY score DESC) AS row_num,userid from users) AS t \r\n"
              + "WHERE userid = ?;";
      var statement = connection.prepareStatement(sql);
      statement.setInt(1, user.getID());
      var resultSet = statement.executeQuery();
      while (resultSet.next())
        rank = resultSet.getInt(1);

      user.setRank(rank);

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

}
