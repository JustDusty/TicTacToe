package tictactoe.model.login;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

  @Override
  public void delete(User u) {
    var connection = Database.getInstance().getConnection();
    try {
      var statement = connection.prepareStatement("Delete from users where userid=?");
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
      var resultSet = statement.executeQuery("Select userid,username,wins,losses From users");

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

  @Override
  public void save(User u) {
    var connection = Database.getInstance().getConnection();
    try {
      var statement =
          connection.prepareStatement("insert into users (userid, username, wins, losses,score) "
              + "values (?,?,?,?,?) on duplicate key update username = values(username), wins = values(wins), losses = values(losses),score = values(score);");

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

}