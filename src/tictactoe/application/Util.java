package tictactoe.application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class Util {

  public static final Dimension smallPanelSize = new Dimension(300, 170);
  public static final Dimension largePanelSize = new Dimension(410, 530);

  private Util() {}


  private static void setFLFontStyle(Component comp) {
    String fl = "FlatLaf.style";
    try {
      if (comp instanceof JButton btn)
        btn.putClientProperty(fl, "font: 130% $light.font");
      if (comp instanceof JLabel lbl)
        lbl.putClientProperty(fl, "font: 150% $light.font");
      if (comp instanceof JTextField txt)
        txt.putClientProperty(fl, "font: 140% $light.font");
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a virtually unique large integer using the built-in UUID class in java
   * 
   * @return the ID
   */
  public static int generateUniqueId() {
    UUID idOne = UUID.randomUUID();
    String str = "" + idOne;
    int uid = str.hashCode();
    String filterStr = "" + uid;
    str = filterStr.replace("-", "");
    return Integer.parseInt(str);
  }


  /**
   * This method will apply the {@link #setFLFontStyle(Component) chosen} FlatLaf fontStyle to
   * buttons, labels and textfields of a JPanel
   * 
   * @param panel : The JPanel containing the components to be modified
   */
  public static void setFontStyle(JPanel panel) {
    for (Component comp : panel.getComponents())
      setFLFontStyle(comp);
  }


  /**
   * Method to link to Swing containers to make them behave similarly.
   * 
   * If the parent window is moved, the child window will keep the same position relative to its
   * parent. If the parent window is closed or resized the same will happen to the child window
   * 
   * @param main : the parent window
   * @param secondary : the child window
   */
  public static void syncUIComponents(Window main, Window secondary) {
    secondary.setLocationRelativeTo(main);
    secondary.setVisible(true);

    main.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        secondary.dispose();
      }

      @Override
      public void windowClosing(WindowEvent e) {
        secondary.dispose();
      }

      @Override
      public void windowDeiconified(WindowEvent e) {
        secondary.setVisible(true);
      }

      @Override
      public void windowIconified(WindowEvent e) {
        secondary.setVisible(false);
      }

      @Override
      public void windowOpened(WindowEvent e) {
        secondary.setEnabled(true);
      }

    });

    main.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentHidden(ComponentEvent e) {
        secondary.setVisible(false);
      }

      @Override
      public void componentMoved(ComponentEvent e) {
        secondary.setLocationRelativeTo(main);
      }
    });

  }

}
