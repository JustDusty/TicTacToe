package tictactoe.controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Controller {
  protected Controller() {}

  protected static void maskFrame(JFrame frame) {
    JPanel frameMask = new JPanel() {
      private static final long serialVersionUID = 1L;

      @Override
      public void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
      }
    };

    frameMask.setOpaque(false);
    frameMask.setLayout(new GridLayout());
    frameMask.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        e.consume();
        Toolkit.getDefaultToolkit().beep();
      }
    });
    frame.setGlassPane(frameMask);
    frameMask.setVisible(true);
  }

  protected static void unmaskFrame(JFrame frame) {
    frame.getGlassPane().setVisible(false);

  }

}
