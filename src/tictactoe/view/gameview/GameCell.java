package tictactoe.view.gameview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;



public class GameCell extends JButton {
  private abstract class MyShape {
    protected static final int DIM = 200;

    protected Color color = Color.LIGHT_GRAY;

    protected abstract void draw(Graphics2D g2);

    protected int getSize() {
      return DIM;
    }


    /**
     * Makes the painted component in a container look smoother and less pixelated
     * 
     * @param g2 : Graphics2D object
     */
    protected void setAntiAliasing(Graphics2D g2) {
      g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
          RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
          RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
          RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
          RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

    }
  }
  private class ShapeO extends MyShape {
    @Override
    public void draw(Graphics2D g2) {
      g2.setColor(color);
      setAntiAliasing(g2);
      g2.drawOval(getSize() / 10, getSize() / 10, getSize() - 2 * getSize() / 10,
          getSize() - 2 * getSize() / 10);
    }
  }

  private class ShapeX extends MyShape {
    @Override
    public void draw(Graphics2D g2) {
      g2.setColor(color);
      setAntiAliasing(g2);
      g2.drawLine(getSize() / 10, getSize() / 10, getSize() - getSize() / 10,
          getSize() - getSize() / 10);
      g2.drawLine(getSize() - getSize() / 10, getSize() / 10, getSize() / 10,
          getSize() - getSize() / 10);
    }
  }

  private static final long serialVersionUID = 1L;

  private static int c = 0;

  private int cellID;

  private String cellValue;

  public GameCell() {
    this.cellID = c++;
    this.cellValue = "none";
  }

  private void setClicked() {
    setEnabled(false);
    repaint();
    validate();
  }

  public int getCellID() {
    return cellID;
  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(200, 200);
  }

  /**
   * Draws an O shape if the cell has been marked by the O player, and X if the cell has been marked
   * by the X player.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();
    if (cellValue.equals("o")) {
      ShapeO shapeO = new ShapeO();
      shapeO.draw(g2);
    } else if (cellValue.equals("x")) {
      ShapeX shapeX = new ShapeX();
      shapeX.draw(g2);
    } else if (cellValue.equals("none"))
      super.paintComponent(g2);

    g2.dispose();

  }

  /**
   * Draws a shape on the cell based on the given value, "x" or "o", and makes the cell unclickable,
   * indicating the cell has been played already.
   * 
   * @param value
   */
  public void setCellValue(String value) {
    setClicked();
    this.cellValue = value;
  }

}
