package tictactoe.view.gameview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import tictactoe.application.GameConstants;

public class GamePanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private static final String FONT = "h1.font";


  private GameView gameView;

  private TitledBorder playerDisplay;
  private TitledBorder difficultyDisplay;

  private JLabel winsLabel;
  private JLabel lossesLabel;
  private JButton btnMenu;
  private JButton btnDifficulty;
  private JButton btnReset;

  private JMenuItem itemEasy;

  private JMenuItem itemHard;

  /**
   * Constructor panel containing the main view of the application, representing the actual game.
   * The panel also contains all the buttons responsible for manipulating the game settings, as well
   * as updating labels indicating the current state of the game.
   */
  public GamePanel() {

    this.gameView = new GameView();
    createGamePanel();

  }

  private TitledBorder createDifficultyDisplay() {
    TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
        "Difficulty: Easy", TitledBorder.CENTER, TitledBorder.BOTTOM);
    border.setTitleFont(UIManager.getFont(FONT));
    return border;
  }


  private JPopupMenu createDifficultyPopup() {
    String[] fontStyle = {"FlatLaf.style", "font: 100% $light.font"};

    JPopupMenu popupDifficulty = new JPopupMenu();
    itemEasy = new JMenuItem("Easy");
    itemEasy.putClientProperty(fontStyle[0], fontStyle[1]);
    itemEasy.setPreferredSize(new Dimension(140, 30));
    itemHard = new JMenuItem("Challenging");
    itemHard.putClientProperty(fontStyle[0], fontStyle[1]);
    itemHard.setPreferredSize(new Dimension(140, 30));
    popupDifficulty.add(itemEasy);
    popupDifficulty.add(itemHard);
    btnDifficulty.addActionListener(e -> popupDifficulty.show(btnDifficulty, 0, 25));
    return popupDifficulty;
  }

  private void createGamePanel() {
    JPanel mainPanel = new JPanel();
    JPanel scorePanel = createScorePanel();
    JPanel optionsPanel = createOptionsPanel();
    createDifficultyPopup();



    playerDisplay = createPlayerDisplay();
    difficultyDisplay = createDifficultyDisplay();

    gameView.setBorder(playerDisplay);
    mainPanel.setBorder(difficultyDisplay);

    mainPanel.add(scorePanel, BorderLayout.WEST);
    mainPanel.add(gameView, BorderLayout.CENTER);
    mainPanel.add(optionsPanel, BorderLayout.EAST);
    add(mainPanel);

  }

  private JPanel createOptionsPanel() {
    String[] fontStyle = {"FlatLaf.style", "font: 140% $light.font"};


    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new GridLayout(4, 0));


    optionsPanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
            "Options", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    TitledBorder border = (TitledBorder) optionsPanel.getBorder();
    border.setTitleFont(UIManager.getFont("h2.regular.font"));



    btnReset = new JButton("Restart");
    btnReset.putClientProperty(fontStyle[0], fontStyle[1]);
    optionsPanel.add(btnReset);



    btnDifficulty = new JButton("Change Difficulty");
    btnDifficulty.putClientProperty(fontStyle[0], fontStyle[1]);
    optionsPanel.add(btnDifficulty);

    Component strut = Box.createVerticalStrut(0);
    optionsPanel.add(strut);


    btnMenu = new JButton("Open Menu");
    btnMenu.putClientProperty(fontStyle[0], fontStyle[1]);
    optionsPanel.add(btnMenu);

    return optionsPanel;
  }


  private TitledBorder createPlayerDisplay() {
    TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
        "Current Player: ", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);
    border.setTitleFont(UIManager.getFont(FONT));
    return border;

  }

  private JPanel createScorePanel() {
    JPanel scorePanel = new JPanel();
    Font font = UIManager.getFont(FONT);
    scorePanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(0, 7, 0, 7);
    JLabel winsText = new JLabel("Total Wins   : ");
    JLabel lossesText = new JLabel("Total Losses : ");
    winsText.setFont(font);
    lossesText.setFont(font);

    winsLabel = new JLabel("0");
    lossesLabel = new JLabel("0");
    winsLabel.setFont(font);
    lossesLabel.setFont(font);

    c.gridx = 0;
    c.gridy = 0;
    scorePanel.add(winsText, c);

    c.gridx = 1;
    c.gridy = 0;
    scorePanel.add(winsLabel, c);

    c.gridx = 0;
    c.gridy = 1;
    scorePanel.add(lossesText, c);

    c.gridx = 1;
    c.gridy = 1;
    scorePanel.add(lossesLabel, c);
    scorePanel
        .setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1),
            "Score", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    TitledBorder border = (TitledBorder) scorePanel.getBorder();
    border.setTitleFont(UIManager.getFont("h2.regular.font"));

    return scorePanel;
  }



  public JButton getBtnDifficulty() {
    return btnDifficulty;
  }



  public JButton getBtnMenu() {
    return btnMenu;
  }

  public JButton getBtnReset() {
    return btnReset;
  }

  public TitledBorder getDifficultyDisplay() {
    return difficultyDisplay;
  }

  public GameView getGameView() {
    return gameView;
  }

  public JMenuItem getItemEasy() {
    return itemEasy;
  }

  public JMenuItem getItemHard() {
    return itemHard;
  }

  public JLabel getLossesLabel() {
    return lossesLabel;
  }

  public TitledBorder getPlayerDisplay() {
    return playerDisplay;
  }

  public JLabel getWinsLabel() {
    return winsLabel;
  }

  public void registerActionListener(ActionListener controller) {
    btnMenu.setActionCommand(GameConstants.MENU);
    btnReset.setActionCommand(GameConstants.RESET);
    itemEasy.setActionCommand(GameConstants.EASY);
    itemHard.setActionCommand(GameConstants.HARD);

    btnMenu.addActionListener(controller);
    btnReset.addActionListener(controller);
    itemEasy.addActionListener(controller);
    itemHard.addActionListener(controller);

  }

}
