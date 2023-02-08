package tictactoe.view.login;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import tictactoe.controllers.LoginListener;

public class LoginFrame extends JDialog {
  class DynamicCardLayout extends CardLayout {
    private static final long serialVersionUID = 1L;

    public Component findCurrentComponent(Container parent) {
      for (Component comp : parent.getComponents())
        if (comp.isVisible())
          return comp;
      return null;
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {

      Component current = findCurrentComponent(parent);
      if (current != null) {
        Insets insets = parent.getInsets();
        Dimension pref = current.getPreferredSize();
        pref.width += insets.left + insets.right;
        pref.height += insets.top + insets.bottom;
        return pref;
      }
      return super.preferredLayoutSize(parent);
    }


  }

  private static final long serialVersionUID = 1L;

  private JPanel mainPanel;
  private WelcomePanel welcomePanel;
  private DifficultyPanel difficultyPanel;
  private GamemodePanel gamemodePanel;
  private AuthPanel authPanel;
  private LeaderPanel leaderPanel;

  public LoginFrame() {

    mainPanel = new JPanel();
    CardLayout card = new DynamicCardLayout();
    mainPanel.setLayout(card);

    welcomePanel = new WelcomePanel();
    difficultyPanel = new DifficultyPanel();
    gamemodePanel = new GamemodePanel();
    authPanel = new AuthPanel();
    leaderPanel = new LeaderPanel();

    mainPanel.add(welcomePanel);
    mainPanel.add(difficultyPanel);
    mainPanel.add(gamemodePanel);
    mainPanel.add(authPanel);
    mainPanel.add(leaderPanel);

    add(mainPanel);

    initializeLoginFrame();

  }

  public AuthPanel getAuthPanel() {
    return authPanel;
  }

  public DifficultyPanel getDifficultyPanel() {
    return difficultyPanel;
  }

  public GamemodePanel getGamemodePanel() {
    return gamemodePanel;
  }

  public LeaderPanel getLeaderPanel() {
    return leaderPanel;
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public WelcomePanel getWelcomePanel() {
    return welcomePanel;
  }

  public void initializeLoginFrame() {
    setUndecorated(true);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setAlwaysOnTop(true);
    pack();
    setVisible(true);

  }

  public void registerActionListener(ActionListener controller) {

    welcomePanel.registerActionListener(controller);
    difficultyPanel.registerActionListener(controller);
    gamemodePanel.registerActionListener(controller);
    authPanel.registerActionListener(controller);
    leaderPanel.registerActionListener(controller);


  }

  public void registerLoginListener(LoginListener listener) {
    leaderPanel.addLoginListener(listener);
  }



}
