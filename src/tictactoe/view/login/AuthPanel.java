package tictactoe.view.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tictactoe.application.GameConstants;
import tictactoe.application.Util;

public class AuthPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private JTextField txtUser;
  private JButton btnSelectExisting;
  private JButton btnQuit;

  private JButton btnPrevious;



  public AuthPanel() {
    createAuthPanel();

  }

  private void createAuthPanel() {

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    btnSelectExisting = new JButton("Select Existing User");
    c.insets = new Insets(1, 1, 1, 1);
    c.gridx = 1;
    c.gridy = 0;
    c.gridwidth = 2;
    mainPanel.add(btnSelectExisting, c);

    JLabel orLbl = new JLabel("Or");
    c.gridx = 1;
    c.gridy = 1;
    mainPanel.add(orLbl, c);

    txtUser = new JTextField("Enter New User");
    txtUser.setForeground(Color.GRAY);
    txtUser.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        txtUser.setText("");
      }

      @Override
      public void focusLost(FocusEvent e) {
        txtUser.setText("Enter New Username");

      }
    });

    c.gridx = 1;

    c.gridy = 2;
    c.gridwidth = 2;
    mainPanel.add(txtUser, c);

    Component strut = Box.createVerticalStrut(18);
    c.gridx = 1;
    c.gridy = 3;
    mainPanel.add(strut, c);

    btnQuit = new JButton("Quit");
    c.insets = new Insets(0, 0, 0, 4);
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


  public JButton getBtnPrevious() {
    return btnPrevious;
  }



  public JButton getBtnQuit() {
    return btnQuit;
  }

  public JButton getBtnSelect() {
    return btnSelectExisting;
  }

  @Override
  public Dimension getPreferredSize() {
    return Util.smallPanelSize;
  }


  public JTextField getTxtUser() {
    return txtUser;
  }

  public void registerActionListener(ActionListener listener) {
    btnSelectExisting.setActionCommand(GameConstants.NEXT);
    btnPrevious.setActionCommand(GameConstants.PREVIOUS);
    btnQuit.setActionCommand(GameConstants.QUIT);
    txtUser.setActionCommand(GameConstants.ENTER_USER + GameConstants.NEXT);

    btnSelectExisting.addActionListener(listener);
    btnQuit.addActionListener(listener);
    btnPrevious.addActionListener(listener);
    txtUser.addActionListener(listener);
  }



}
