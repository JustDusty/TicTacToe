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
   * human-like algorithm, finds the best choice for the current state of the game
   *
   * @param player
   * @param opponent
   *
   */
  public static int findOptimalChoice(GameBoard gameBoard, String player, String opponent) {
    List<Integer> moves = gameBoard.getPossibleMoves();

    // finds a move to play if the game is still ongoing
    if (gameBoard.getWinner().equals("unknown")) {

      // if no moves have been played so far, play a random cell
      if (moves.size() == 9)
        return getRandomMove(moves);

      // checks board for potential player wins as priority move
      int playerMove = findWinningCombination(gameBoard, player);
      if (playerMove != -1)
        return playerMove;

      // checks board for potential opponent wins, and blocks it
      int opponentMove = findWinningCombination(gameBoard, opponent);
      if (opponentMove != -1)
        return opponentMove;

      // if no move is found, play a random move
      return getRandomMove(moves);
    }
    return -1;
  }

  /**
   * selects a random move from available cells
   *
   * @param moves
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
