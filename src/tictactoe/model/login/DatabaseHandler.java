package tictactoe.model.login;

import java.util.List;

public class DatabaseHandler {
  public static final UserDao USER_DAO = new UserDaoImpl();
  private UserTableModel model;

  public DatabaseHandler(UserTableModel model) {
    this.model = model;
  }


  public void readFromDatabase() {
    List<User> list = USER_DAO.getAll();
    model.add(list);
  }


  public void saveToDatabase() {
    List<User> list = model.getRows();
    for (User user : list)
      USER_DAO.save(user);
  }



}
