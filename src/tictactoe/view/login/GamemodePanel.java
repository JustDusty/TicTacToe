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

public class GamemodePanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private JButton btnLogin;
  private JButton btnGuest;
  private JButton btnQuit;

  private JButton btnPrevious;

  public GamemodePanel() {
    createGamemodePanel();
  }

  private void createGamemodePanel() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    btnLogin = new JButton("Login");
    c.insets = new Insets(2, 2, 2, 2);
    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 1;
    c.gridy = 0;
    c.gridwidth = 2;
    mainPanel.add(btnLogin, c);

    JLabel orLbl = new JLabel("Or");
    c.gridx = 1;
    c.gridy = 1;
    mainPanel.add(orLbl, c);

    btnGuest = new JButton("Play As Guest");
    c.gridx = 1;
    c.gridy = 2;
    mainPanel.add(btnGuest, c);

    Component strut = Box.createVerticalStrut(10);
    c.gridx = 1;
    c.gridy = 3;
    mainPanel.add(strut, c);

    btnQuit = new JButton("Quit");
    c.insets = new Insets(0, 0, 0, 4);
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 4;
    c.gridwidth = 1;
    mainPanel.add(btnQuit, c);

    btnPrevious = new JButton("Go Back");
    c.gridx = 2;
    c.gridy = 4;
    mainPanel.add(btnPrevious, c);

    add(mainPanel);
    Util.setFontStyle(mainPanel);

  }

  public JButton getBtnGuest() {
    return btnGuest;
  }

  public JButton getBtnLogin() {
    return btnLogin;
  }


  public JButton getBtnPrevious() {
    return btnPrevious;
  }

  public JButton getBtnQuit() {
    return btnQuit;
  }



  @Override
  public Dimension getPreferredSize() {
    return Util.smallPanelSize;
  }

  public void registerActionListener(ActionListener listener) {
    btnLogin.setActionCommand(GameConstants.NEXT);
    btnPrevious.setActionCommand(GameConstants.PREVIOUS);
    btnGuest.setActionCommand(GameConstants.PLAY_AS_GUEST);
    btnQuit.setActionCommand(GameConstants.QUIT);

    btnLogin.addActionListener(listener);
    btnGuest.addActionListener(listener);
    btnQuit.addActionListener(listener);
    btnPrevious.addActionListener(listener);
  }

}
