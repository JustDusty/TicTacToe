package tictactoe.application;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import tictactoe.controllers.DialogController;
import tictactoe.controllers.GameController;
import tictactoe.controllers.OptionsController;
import tictactoe.model.GameBoard;
import tictactoe.model.LoginHandler;
import tictactoe.model.User;
import tictactoe.view.gameview.GamePanel;
import tictactoe.view.login.LeaderPanel;
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

  static void initializeApp() {
    user = new User("Guest");
    optionsController.setGameController(gameController);
    gameController.setUser(user);
    optionsController.setUser(user);
    loginFrame.registerActionListener(optionsController);
    loginFrame.registerLoginListener(optionsController);
    loginFrame.registerLoginListener(gameController);

  }

  static void initLoginHandler(LoginFrame loginFrame) {
    LeaderPanel leaderPanel = loginFrame.getLeaderPanel();
    LoginHandler handler = new LoginHandler(leaderPanel.getUserTableModel());
    handler.read();

    Runtime.getRuntime().addShutdownHook(new Thread(handler::write));

  }


  static void runApp() {
    user = new User("Guest");
    gameBoard = new GameBoard();
    gamePanel = new GamePanel();

    gameFrame = createGameFrame(gamePanel);
    loginFrame = new LoginFrame();
    Util.syncUIComponents(gameFrame, loginFrame);

    gameController = new GameController(gamePanel, gameBoard);
    optionsController = new OptionsController(gamePanel, gameBoard);
    dialogController = new DialogController(loginFrame, gamePanel);

    initializeApp();
    initLoginHandler(loginFrame);

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
