package tictactoe.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author HP
 *
 */
public class UserTableModel extends AbstractTableModel {
  private static final long serialVersionUID = 1L;
  protected static final String[] COLUMNS = {"Username", "Score", "Wins/Losses"};

  private List<User> rows;

  public UserTableModel() {
    rows = new ArrayList<>();
  }

  public void add(List<User> users) {
    rows.addAll(users);
    fireTableDataChanged();
  }

  public void add(User user) {
    rows.add(user);
    fireTableDataChanged();
  }

  @Override
  public int getColumnCount() {
    return COLUMNS.length;
  }


  @Override
  public String getColumnName(int column) {
    return COLUMNS[column];
  }

  @Override
  public int getRowCount() {
    return rows.size();
  }

  public List<User> getRows() {
    return rows;
  }

  public User getUserDataAt(int row) {
    return rows.get(row);
  }

  @Override
  public Object getValueAt(int row, int column) {
    User user = getUserDataAt(row);
    return switch (column) {

      case 0 -> user.getName();
      case 1 -> user.getScore();
      case 2 -> user.getWins() + "/" + user.getLosses();
      default -> null;
    };
  }

}
