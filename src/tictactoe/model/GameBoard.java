package tictactoe.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import tictactoe.application.GameConstants;


public class GameBoard {
  private List<String> cells;

  private String human;
  private String computer;
  private String currentPlayer;

  private String currentDifficulty = GameConstants.EASY;
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  /** GameBoard represented as list of 9 elements */
  public GameBoard() {
    initializeBoard();

  }

  /**
   * Creates clone of original board
   *
   * @param original
   */
  public GameBoard(GameBoard original) {
    this.cells = new ArrayList<>();
    for (int i = 0; i < 9; i++)
      cells.add(original.get(i));
  }

  public static String getNextTurn(String string) {
    return string.equals("o") ? "x" : "o";
  }

  public int computerMove() {
    int move = -1;

    if (getCurrentDifficulty().equals(GameConstants.EASY))
      move = GameAlgorithm.getRandomMove(getPossibleMoves());
    else if (getCurrentDifficulty().equals(GameConstants.HARD))
      move = GameAlgorithm.findOptimalChoice(this, computer, human);
    return move;
  }



  /**
   * @param i
   * @return value of cell at index i
   */
  public String get(int i) {
    return cells.get(i);
  }

  public String getBoardState() {
    return "," + get(0) + get(1) + get(2) + "," + get(3) + get(4) + get(5) + "," + get(6) + get(7)
        + get(8)

        + "," + get(0) + get(3) + get(6) + "," + get(1) + get(4) + get(7) + "," + get(2) + get(5)
        + get(8)

        + "," + get(0) + get(4) + get(8) + "," + get(2) + get(4) + get(6);
  }

  /** gets cells in board as a list */
  public List<String> getCells() {
    return cells;
  }


  public String getComputerPlayer() {
    return computer;
  }


  public String getCurrentDifficulty() {
    return currentDifficulty;
  }

  public String getCurrentPlayer() {
    return currentPlayer;
  }

  public String getHumanPlayer() {
    return human;
  }

  /**
   *
   * @return a list of indexes of all cells with "none" values
   */
  public List<Integer> getPossibleMoves() {
    List<Integer> empty = new ArrayList<>();
    for (int i = 0; i < cells.size(); i++)
      if (cells.get(i).equals("none"))
        empty.add(i);
    return empty;
  }



  /**
   * Calculates the winner based on the current board state
   *
   * @param board
   * @return "x" or "o" or "draw" or "unknown" if state is not final
   */
  public String getWinner() {
    String boardState = getBoardState();
    if (boardState.contains("xxx"))
      return "x";
    else if (boardState.contains("ooo"))
      return "o";
    else if (boardState.length() <= 33)
      return "draw";
    else
      return "unknown";
  }

  /**
   * Initialises board with 9 "none" Strings
   */
  public void initializeBoard() {
    setCurrentPlayer("x");
    cells = new ArrayList<>();
    for (int i = 0; i < 9; i++)
      cells.add("none");

    human = ThreadLocalRandom.current().nextBoolean() ? "x" : "o";
    computer = GameBoard.getNextTurn(human);

  }

  public boolean isGameOver() {
    return !getWinner().equals("unknown");

  }

  public void registerPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  /**
   * resets board to initial state
   */
  public void resetBoard() {
    initializeBoard();
  }

  public void setCurrentDifficulty(String difficulty) {
    String old = this.currentDifficulty;
    this.currentDifficulty = difficulty;
    pcs.firePropertyChange("currentDifficulty", old, difficulty);
  }


  public void setCurrentPlayer(String player) {
    String old = this.currentPlayer;
    this.currentPlayer = player;
    pcs.firePropertyChange("currentPlayer", old, player);
  }

  /**
   * Change a value in the board by index, to specified value
   *
   * @param index
   * @param value takes "o" or "x" or "none"
   */
  public void update(int index, String value) {
    getCells().set(index, value);
    setCurrentPlayer(getNextTurn(value));
  }



}
