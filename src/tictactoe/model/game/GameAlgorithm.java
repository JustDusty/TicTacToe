package tictactoe.model.game;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GameAlgorithm {
  private static int[][] combinations =
      {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

  private GameAlgorithm() {}



  /**
   * checks board for potential winning combination and returns the move that would complete it, or
   * block it, depending on the parameter
   *
   * @param combinations : all possible game winning combinations
   * @param player : human or computer
   *
   */
  private static int findWinningCombination(GameBoard gameBoard, String player) {
    for (int[] combination : combinations) {
      int playerCount = 0;
      int emptyIndex = -1;
      for (int i : combination)
        if (gameBoard.get(i).equals(player))
          playerCount++;
        else if (gameBoard.get(i).equals("none"))
          emptyIndex = i;
      if (playerCount == 2 && emptyIndex != -1)
        return emptyIndex;
    }
    return -1;
  }

  /**
   * human-like algorithm, finds the best choice for the player for the current state of the
   * game.<br>
   * The method checks if the game is ongoing.<br>
   * The algorithm will prioritize looking for a winning move. If no winning move is found, the
   * algorithm will find a move to block the opponent's winning move. If the opponent has no winning
   * options, the algorithm will find the move to complete two cells in a row. <br>
   * If none of these conditions are met the algorithm will return a random move on the board in the
   * empty cells.
   *
   * @param gameBoard : the current state of the board
   * @param player : the player that will play this move
   * @param opponent : the opponent
   *
   * @see #findWinningCombination(GameBoard, String) findWinningCombination
   */
  public static int findOptimalChoice(GameBoard gameBoard, String player, String opponent) {
    List<Integer> moves = gameBoard.getPossibleMoves();

    if (gameBoard.getWinner().equals("unknown")) {

      if (moves.size() == 9)
        return getRandomMove(moves);

      int playerMove = findWinningCombination(gameBoard, player);
      if (playerMove != -1)
        return playerMove;

      int opponentMove = findWinningCombination(gameBoard, opponent);
      if (opponentMove != -1)
        return opponentMove;

      return getRandomMove(moves);
    }
    return -1;
  }

  /**
   * selects a random move from the cells that have not yet been played
   *
   * @param moves : a list of available moves
   *
   */
  public static int getRandomMove(List<Integer> moves) {
    if (moves.isEmpty())
      return -1;
    else {
      int move = ThreadLocalRandom.current().nextInt(moves.size());
      return moves.get(move);
    }
  }

}
