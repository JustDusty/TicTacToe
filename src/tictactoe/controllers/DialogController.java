package tictactoe.controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import tictactoe.application.GameConstants;
import tictactoe.application.Util;
import tictactoe.model.login.DatabaseHandler;
import tictactoe.model.login.User;
import tictactoe.view.gameview.GamePanel;
import tictactoe.view.login.LoginFrame;

public class DialogController extends Controller implements ActionListener {
  private LoginFrame loginFrame;
  private GamePanel gamePanel;


  public DialogController(LoginFrame loginFrame, GamePanel gamePanel) {
    this.loginFrame = loginFrame;
    this.gamePanel = gamePanel;


    loginFrame.registerActionListener(this);
    gamePanel.registerActionListener(this);



    JFrame gameFrame = (JFrame) SwingUtilities.getWindowAncestor(gamePanel);
    Controller.maskFrame(gameFrame);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    String command = e.getActionCommand();
    JFrame gameFrame = (JFrame) SwingUtilities.getWindowAncestor(gamePanel);

    if (command.contains(GameConstants.QUIT))
      System.exit(0);

    if (command.contains(GameConstants.NEXT))
      goToNext();

    if (command.contains(GameConstants.PREVIOUS))
      goToPrevious();

    if (command.contains(GameConstants.LAST))
      goToLast();

    if (command.contains(GameConstants.PLAY_AS_GUEST)) {
      loginFrame.dispose();
      Controller.unmaskFrame(gameFrame);
      gamePanel.setEnabled(true);
    }
    if (command.contains(GameConstants.CONFIRM_USER)) {
      loginFrame.dispose();
      Controller.unmaskFrame(gameFrame);
      gamePanel.setEnabled(true);

    }

    if (command.contains(GameConstants.MENU)) {
      loginFrame.initializeLoginFrame();
      loginFrame.setLocationRelativeTo(gamePanel);
      Controller.maskFrame(gameFrame);
    }

    if (command.contains(GameConstants.ENTER_USER)) {
      String username = loginFrame.getAuthPanel().getTxtUser().getText();
      User newUser = new User(Util.generateUniqueId(), username);
      loginFrame.getLeaderPanel().getUserTableModel().add(newUser);
      DatabaseHandler.USER_DAO.save(newUser);
    }
  }

  public void goToLast() {
    JPanel mainPanel = loginFrame.getMainPanel();
    CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
    cardLayout.last(mainPanel);
    loginFrame.pack();
    loginFrame.setLocationRelativeTo(gamePanel);
  }

  public void goToNext() {
    JPanel mainPanel = loginFrame.getMainPanel();
    CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
    cardLayout.next(mainPanel);
    loginFrame.pack();
    loginFrame.setLocationRelativeTo(gamePanel);
  }

  public void goToPrevious() {
    JPanel mainPanel = loginFrame.getMainPanel();
    CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
    cardLayout.previous(mainPanel);
    loginFrame.pack();
    loginFrame.setLocationRelativeTo(gamePanel);

  }

}
