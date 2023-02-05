package tictactoe.controllers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tictactoe.application.GameConstants;
import tictactoe.model.game.GameBoard;
import tictactoe.model.login.User;
import tictactoe.view.gameview.GameCell;
import tictactoe.view.gameview.GamePanel;
import tictactoe.view.gameview.GameView;

@SuppressWarnings("unused")


public class GameController implements ActionListener, LoginListener {

  private GameBoard gameBoard;
  private GamePanel gamePanel;
  private GameView gameView;
  private User user;

  public GameController(GamePanel gamePanel, GameBoard gameBoard) {
    this.gameBoard = gameBoard;
    this.gamePanel = gamePanel;

    this.gameView = gamePanel.getGameView();
    gameView.registerActionListener(this);

    gamePanel.addPropertyChangeListener("enabled", e -> {
      boolean enabled = (Boolean) e.getNewValue();
      if (enabled)
        initializeGameController();
    });

  }

  void fireComputerEvent() {

    String player = gameBoard.getCurrentPlayer();

    if (!gameBoard.isGameOver()) {
      int c = gameBoard.computerMove();
      GameCell gameCell = gameView.getCellByID(c);
      gameCell.setCellValue(player);
      gameBoard.update(c, player);
    }

    if (gameBoard.isGameOver())
      fireGameOverEvent();

  }

  void fireGameOverEvent() {
    String winner = gameBoard.getWinner();
    if (winner.equals(gameBoard.getHumanPlayer()))
      user.incrementWins();
    else if (winner.equals(gameBoard.getComputerPlayer()))
      user.incrementLosses();

    String display = String.format("%27s", "Game Over");
    JOptionPane.showMessageDialog(new JFrame(), display);
    EventQueue.invokeLater(this::resetGame);
  }

  void firePlayerEvent(int cellNum) {
    String player = gameBoard.getCurrentPlayer();
    String human = gameBoard.getHumanPlayer();
    String computer = gameBoard.getComputerPlayer();

    if (gameBoard.getCurrentPlayer().equals(human)) {

      GameCell gameCell = gameView.getCellByID(cellNum);
      gameCell.setCellValue(player);
      gameBoard.update(cellNum, human);
    }

    if (gameBoard.getCurrentPlayer().equals(computer)) {
      Timer timer = new Timer();
      TimerTask task = new TimerTask() {
        @Override
        public void run() {
          fireComputerEvent();
        }
      };
      timer.schedule(task, 800);
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof GameCell) {
      int cellNum = Integer.parseInt(e.getActionCommand());
      firePlayerEvent(cellNum);
    }
    if (e.getActionCommand().contains(GameConstants.RESET)) {
      String display = String.format("%28s", "Are you sure?");
      boolean confirmed = JOptionPane.showConfirmDialog(new JFrame(), display, "",
          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
      if (confirmed)
        resetGame();
    }
  }

  /**
   * sets game controller to initial state, assigns random X and O to human and computer.
   *
   * {@link #fireComputerEvent()} if computer gets first turn
   */
  public void initializeGameController() {
    if (gameBoard.getComputerPlayer().equals("x"))
      fireComputerEvent();
  }

  /**
   * sets gameBoard, UI and controller to initial state
   *
   */
  public void resetGame() {
    gameView.resetView();
    gameBoard.resetBoard();
    initializeGameController();

  }

  public void setUser(User user) {
    this.user = user;
  }


  @Override
  public void userSelected(User user) {
    this.user = user;
  }
}
