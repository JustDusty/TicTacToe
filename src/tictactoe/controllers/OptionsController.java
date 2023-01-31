package tictactoe.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tictactoe.application.GameConstants;
import tictactoe.model.GameBoard;
import tictactoe.model.User;
import tictactoe.view.gameview.GamePanel;

public class OptionsController extends Controller
    implements PropertyChangeListener, ActionListener, LoginListener {
  private GameController gameController;
  private GameBoard gameBoard;
  private GamePanel gamePanel;

  private User user;

  public OptionsController(GamePanel gamePanel, GameBoard gameBoard) {
    this.gameBoard = gameBoard;
    this.gamePanel = gamePanel;
    gameBoard.registerPropertyChangeListener(this);
    gamePanel.registerActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.contains(GameConstants.RESET)) {
      String display = String.format("%28s", "Are you sure?");
      boolean confirmed = JOptionPane.showConfirmDialog(new JFrame(), display, "",
          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
      if (confirmed)
        gameController.resetGame();
    }
    if (command.contains(GameConstants.EASY))
      gameBoard.setCurrentDifficulty(GameConstants.EASY);
    if (command.contains(GameConstants.HARD))
      gameBoard.setCurrentDifficulty(GameConstants.HARD);
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    if (event.getPropertyName().equals("currentPlayer")) {
      String title = (String) event.getNewValue();
      gamePanel.getPlayerDisplay().setTitle("Current Player: " + title.toUpperCase());
      gamePanel.repaint();
    }
    if (event.getPropertyName().equals("currentDifficulty")) {
      String title = (String) event.getNewValue();
      gamePanel.getDifficultyDisplay().setTitle("Difficulty: " + title);
      gamePanel.repaint();
    }
    if (event.getPropertyName().equals("roundWin") || event.getPropertyName().equals("winsUser")) {
      int wins = (int) event.getNewValue();
      gamePanel.getWinsLabel().setText(String.valueOf(wins));
      gamePanel.repaint();
    }
    if (event.getPropertyName().equals("roundLoss")
        || event.getPropertyName().equals("lossesUser")) {
      int losses = (int) event.getNewValue();
      gamePanel.getLossesLabel().setText(String.valueOf(losses));
      gamePanel.repaint();
    }

  }

  public void setGameController(GameController controller) {
    this.gameController = controller;

  }

  public void setUser(User user) {
    this.user = user;
    user.registerPropertyChangeListener(this);
  }

  @Override
  public void userSelected(User user) {
    this.user = user;
    this.user.registerPropertyChangeListener(this);

    gamePanel.getWinsLabel().setText(String.valueOf(user.getWins()));
    gamePanel.getLossesLabel().setText(String.valueOf(user.getLosses()));
    gamePanel.repaint();

  }

}
