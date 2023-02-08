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

public class WelcomePanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private JButton btnComputer;
  private JButton btnLast;

  private JButton btnQuit;

  /**
   * The introductory panel. Contains buttons to view the {@link LeaderPanel leaderboard panel} or
   * continue to the next panel
   */
  public WelcomePanel() {
    createWelcomeFrame();
  }

  private void createWelcomeFrame() {

    JPanel mainPanel = new JPanel();

    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    btnComputer = new JButton("Play Against Computer");

    c.insets = new Insets(2, 2, 2, 2);
    c.gridx = 0;
    c.gridy = 0;
    mainPanel.add(btnComputer, c);

    JLabel orLbl = new JLabel("Or");
    c.gridx = 0;
    c.gridy = 1;
    mainPanel.add(orLbl, c);


    btnLast = new JButton("View Leaderboard");
    c.gridx = 0;
    c.gridy = 2;
    mainPanel.add(btnLast, c);

    Component strut = Box.createVerticalStrut(18);
    c.gridx = 0;
    c.gridy = 3;
    mainPanel.add(strut, c);

    btnQuit = new JButton("Quit");

    c.gridx = 0;
    c.gridy = 4;
    mainPanel.add(btnQuit, c);

    add(mainPanel);

    Util.setFontStyle(mainPanel);

  }

  public JButton getBtnComputer() {
    return btnComputer;
  }

  public JButton getBtnLast() {
    return btnLast;
  }


  public JButton getBtnQuit() {
    return btnQuit;
  }

  @Override
  public String getName() {
    return "WelcomePanel";
  }


  @Override
  public Dimension getPreferredSize() {
    return Util.smallPanelSize;
  }

  public void registerActionListener(ActionListener listener) {
    btnComputer.setActionCommand(GameConstants.NEXT);
    btnLast.setActionCommand(GameConstants.LAST);
    btnQuit.setActionCommand(GameConstants.QUIT);

    btnComputer.addActionListener(listener);
    btnLast.addActionListener(listener);
    btnQuit.addActionListener(listener);
  }

}
