package tictactoe.application;

public final class GameConstants {

  /**
   * quit command
   */
  public static final String QUIT = "quit";

  /**
   * Command for going to the next panel in the card layout.
   */
  public static final String NEXT = "next";

  /**
   * Command for going to the previous panel in the card layout.
   */
  public static final String PREVIOUS = "previous";

  /**
   * Command for going to the leaderboard panel
   */
  public static final String LAST = "last";

  /**
   * Command for opening the menu where the user left off.
   */
  public static final String MENU = "menu";

  public static final String RESET = "reset";

  /**
   * Command to change the difficulty of the game to easy difficulty
   */
  public static final String EASY = "easy";

  /**
   * Command to change the difficulty of the game to hard difficulty
   */
  public static final String HARD = "hard";

  /**
   * Command for playing the game without logging in
   */
  public static final String PLAY_AS_GUEST = "playasguest";

  /**
   * Command for logging in using the selected user
   */
  public static final String CONFIRM_USER = "confirmuserselection";

  /**
   * Commanding for adding a new user
   */
  public static final String ENTER_USER = "enteruser";

  /**
   * Command for deleting an existing user
   */
  public static final String DELETE_USER = "deleteuser";

  private GameConstants() {}
}
