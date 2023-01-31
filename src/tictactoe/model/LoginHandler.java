package tictactoe.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginHandler {
  private static final Logger LOGGER = Logger.getLogger("GameLog");;
  private static final String FILENAME = "leaderboard.dat";
  private UserTableModel model;

  public LoginHandler(UserTableModel model) {

    this.model = model;
  }

  @SuppressWarnings("unchecked")
  public void read() {
    List<User> list = null;

    try (FileInputStream fis = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);) {

      list = (List<User>) ois.readObject();
      model.add(list);
    } catch (FileNotFoundException e) {
      LOGGER.log(Level.WARNING,
          "No data file found in root directory. 'leaderboard.dat' will be created on exit.");
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }


  public void write() {
    List<User> list = model.getRows();
    try (FileOutputStream fos = new FileOutputStream(FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);) {

      oos.writeObject(list);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
