package tictactoe.view.login;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tictactoe.application.GameConstants;
import tictactoe.application.Util;

public class DifficultyPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JButton btnEasy;
  private JButton btnHard;
  private JButton btnQuit;

  private JButton btnPrevious;



  public DifficultyPanel() {
    createDifficultyPanel();
  }

  public void createDifficultyPanel() {

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    JLabel difficultyLabel = new JLabel("Difficulty");
    c.insets = new Insets(0, 3, 5, 3);
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    mainPanel.add(difficultyLabel, c);

    btnEasy = new JButton("Easy");
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 2;
    mainPanel.add(btnEasy, c);

    btnHard = new JButton("Hard");
    c.gridx = 1;
    c.gridy = 2;
    mainPanel.add(btnHard, c);

    Component strut = Box.createVerticalStrut(30);
    c.gridx = 1;
    c.gridy = 3;
    mainPanel.add(strut, c);

    btnQuit = new JButton("Quit");
    c.gridwidth = 1;
    c.insets = new Insets(0, 2, 0, 2);
    c.anchor = GridBagConstraints.EAST;
    c.gridx = 0;
    c.gridy = 4;

    mainPanel.add(btnQuit, c);

    btnPrevious = new JButton("Go Back");
    c.gridx = 1;
    c.gridy = 4;
    c.anchor = GridBagConstraints.WEST;
    mainPanel.add(btnPrevious, c);

    add(mainPanel);
    Util.setFontStyle(mainPanel);

  }

  public JButton getBtnEasy() {
    return btnEasy;
  }

  public JButton getBtnHard() {
    return btnHard;
  }


  @Override
  public String getName() {
    return "DifficultyPanel";
  }


  @Override
  public Dimension getPreferredSize() {
    return Util.smallPanelSize;
  }

  public void registerActionListener(ActionListener controller) {
    btnEasy.setActionCommand(GameConstants.EASY + GameConstants.NEXT);
    btnHard.setActionCommand(GameConstants.HARD + GameConstants.NEXT);
    btnPrevious.setActionCommand(GameConstants.PREVIOUS);
    btnQuit.setActionCommand(GameConstants.QUIT);

    btnEasy.addActionListener(controller);
    btnHard.addActionListener(controller);
    btnQuit.addActionListener(controller);
    btnPrevious.addActionListener(controller);
  }

}
