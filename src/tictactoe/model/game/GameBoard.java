
package tictactoe.model.game;

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


  /**
   * Helper method that changes the value representing the current player to the opposing value.
   * Changes "x" to "o" and vice versa.
   * 
   * @param string
   * @return
   */
  public static String getNextTurn(String player) {
    return player.equals("o") ? "x" : "o";
  }


  private String getBoardState() {
    return "," + get(0) + get(1) + get(2) + "," + get(3) + get(4) + get(5) + "," + get(6) + get(7)
        + get(8)

        + "," + get(0) + get(3) + get(6) + "," + get(1) + get(4) + get(7) + "," + get(2) + get(5)
        + get(8)

        + "," + get(0) + get(4) + get(8) + "," + get(2) + get(4) + get(6);
  }



  /**
   * Values that have not yet been played by either the computer or the player are represented by a
   * String value equal to "none".
   *
   * @return a list of indexes of all cells with "none" values
   */
  List<Integer> getPossibleMoves() {
    List<Integer> empty = new ArrayList<>();
    for (int i = 0; i < cells.size(); i++)
      if (cells.get(i).equals("none"))
        empty.add(i);
    return empty;
  }


  /**
   * Calculates the optimal move to be made by the AI based on the chosen game difficulty
   * 
   * @return move: the computer's move
   */
  public int computerMove() {
    int move = -1;
    if (getCurrentDifficulty().equals(GameConstants.EASY))
      move = GameAlgorithm.getRandomMove(getPossibleMoves());
    else if (getCurrentDifficulty().equals(GameConstants.HARD))
      move = GameAlgorithm.findOptimalChoice(this, computer, human);
    return move;
  }

  /**
   * Gets the value in the board occupying the position i. Represented as "x" or "o"
   * 
   * @param i
   * @return value of cell at index i
   */
  public String get(int i) {
    return cells.get(i);
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
   * Initialises board with 9 Strings representing an empty board and randomizes the choice for the
   * starting player. The starting players is always "x". if the human player gets x, they will
   * begin first, otherwise the computer plays first.
   * 
   */
  public void initializeBoard() {
    setCurrentPlayer("x");
    cells = new ArrayList<>();
    for (int i = 0; i < 9; i++)
      cells.add("none");

    human = ThreadLocalRandom.current().nextBoolean() ? "x" : "o";
    computer = GameBoard.getNextTurn(human);

  }

  /**
   * Checks if there is a winning combination in the current board.
   * 
   * @return
   */
  public boolean isGameOver() {
    return !getWinner().equals("unknown");

  }

  public void registerPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  /**
   * resets the board to its initial state using the {@link #initializeBoard() initializeBoard}
   * method.
   * 
   */
  public void resetBoard() {
    initializeBoard();
  }


  /**
   * Changes the current difficulty and notifies all registered property change listeners of the
   * change.
   * 
   * @param difficulty
   */
  public void setCurrentDifficulty(String difficulty) {
    String old = this.currentDifficulty;
    this.currentDifficulty = difficulty;
    pcs.firePropertyChange("currentDifficulty", old, difficulty);
  }


  /**
   * Changes the current player and notifies all registered property change listeners of the change.
   * 
   * @param player
   */
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

