package tictactoe.application;

import java.awt.EventQueue;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import tictactoe.controllers.DialogController;
import tictactoe.controllers.GameController;
import tictactoe.controllers.OptionsController;
import tictactoe.model.game.GameBoard;
import tictactoe.model.login.Database;
import tictactoe.model.login.DatabaseHandler;
import tictactoe.model.login.User;
import tictactoe.view.gameview.GamePanel;
import tictactoe.view.login.LoginFrame;

@SuppressWarnings("unused")
public class Application {
  private static User user;
  private static GameBoard gameBoard;
  private static GamePanel gamePanel;

  private static GameController gameController;
  private static OptionsController optionsController;
  private static DialogController dialogController;

  private static JFrame gameFrame;
  private static LoginFrame loginFrame;
  private static Database db = Database.getInstance();

  static JFrame createGameFrame(GamePanel gamePanel) {
    JFrame f = new JFrame();
    f.add(gamePanel);
    gamePanel.setEnabled(false);
    f.setResizable(false);
    f.pack();
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setVisible(true);
    return f;
  }

  static void initApp() {
    optionsController.setGameController(gameController);
    gameController.setUser(user);
    optionsController.setUser(user);
    loginFrame.registerActionListener(optionsController);
    loginFrame.registerLoginListener(optionsController);
    loginFrame.registerLoginListener(gameController);

  }

  static void initDatabase() {
    try {
      db.connect();
      var connection = db.getConnection();
      var statement = connection.createStatement();
      var mysql =
          "CREATE TABLE IF NOT EXISTS users (" + "userid INTEGER PRIMARY KEY  NOT NULL default "
              + user.getID() + ",username TEXT NOT NULL," + "wins INTEGER NOT NULL,"
              + "losses INTEGER NOT NULL," + "score DECIMAL(8,2) NOT NULL," + "UNIQUE (userid));";
      var sqlite = "create table if not exists users "
          + "(userid integer primary key not null UNIQUE default " + user.getID()
          + "+,username text not null," + "wins integer not null," + "losses integer not null,"
          + "score decimal(8,2) not null;";

      statement.execute(mysql);
      statement.close();

      DatabaseHandler dbHandler =
          new DatabaseHandler(loginFrame.getLeaderPanel().getUserTableModel());
      dbHandler.readFromDatabase();

      Runtime.getRuntime().addShutdownHook(new Thread(dbHandler::saveToDatabase));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  static void runApp() {
    user = new User(Util.generateUniqueId(), "Guest");

    gameBoard = new GameBoard();
    gamePanel = new GamePanel();

    gameFrame = createGameFrame(gamePanel);
    loginFrame = new LoginFrame();
    Util.syncUIComponents(gameFrame, loginFrame);

    gameController = new GameController(gamePanel, gameBoard);
    optionsController = new OptionsController(gamePanel, gameBoard);
    dialogController = new DialogController(loginFrame, gamePanel);

    initApp();
    initDatabase();

  }

  public static void main(String[] args) {
    try {
      UIManager.put("TextComponent.arc", 0);
      UIManager.put("Button.arc", 0);
      UIManager.put("Component.arc", 0);
      UIManager.put("CheckBox.arc", 0);
      UIManager.put("ProgressBar.arc", 0);
      UIManager.put("ScrollBar.width", 16);
      UIManager.setLookAndFeel(
          new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme());
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    EventQueue.invokeLater(Application::runApp);


  }

}
