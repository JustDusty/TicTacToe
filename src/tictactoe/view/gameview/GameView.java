package tictactoe.view.gameview;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class GameView extends JPanel {
  private static final long serialVersionUID = 1L;

  private List<GameCell> gameCells;

  /**
   * Creates the main view of the application as a 3x3 grid of {@link #gameCells cells}
   */
  public GameView() {
    gameCells = new ArrayList<>();
    setLayout(new GridLayout(3, 3, 0, 0));
    setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));

    for (int i = 0; i < 9; i++) {
      GameCell gameCell = new GameCell();
      gameCells.add(gameCell);
      gameCell.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
      add(gameCell);
    }

  }


  /**
   * gets the Game Cell that corresponds to the parameter representing the cell's ID value.
   * 
   * @param i
   * @return
   */
  public GameCell getCellByID(int i) {
    return gameCells.get(i);
  }


  public void registerActionListener(ActionListener listener) {
    for (GameCell gameCell : gameCells) {
      String cellID = Integer.toString(gameCell.getCellID());
      gameCell.setActionCommand(cellID);
      gameCell.addActionListener(listener);
    }
  }

  /**
   * Empties all the cells in the board, and makes them clickable.
   * 
   */
  public void resetView() {
    for (GameCell gameCell : gameCells) {
      gameCell.setCellValue("none");
      gameCell.setEnabled(true);
    }
  }
}
