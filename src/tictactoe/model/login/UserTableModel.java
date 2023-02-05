package tictactoe.model.login;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;


public class UserTableModel extends AbstractTableModel {
  private static final long serialVersionUID = 1L;
  protected static final String[] COLUMNS = {"Rank", "Username", "Wins", "Losses", "Score"};
  private JTable userTable;
  private transient TableRowSorter<UserTableModel> sorter;
  private transient List<User> rows;

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

  public JTable createUserTable() {
    userTable = new JTable(this);
    userTable.setShowGrid(false);
    userTable.setShowHorizontalLines(false);
    userTable.setShowVerticalLines(false);
    userTable.setRowMargin(0);
    userTable.setIntercellSpacing(new Dimension(0, 0));
    userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    userTable.setPreferredScrollableViewportSize(new Dimension(370, 400));
    userTable.setFillsViewportHeight(true);
    sorter = new TableRowSorter<>(this);
    sorter.setComparator(0, Comparator.naturalOrder());
    userTable.setRowSorter(sorter);
    return userTable;
  }

  public void delete(User user) {
    rows.remove(user);
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


  public TableRowSorter<UserTableModel> getSorter() {
    return sorter;
  }

  public User getUserDataAt(int row) {
    return rows.get(row);
  }

  public JTable getUserTable() {
    return userTable;
  }

  @Override
  public Object getValueAt(int row, int column) {
    User user = getUserDataAt(row);
    DatabaseHandler.USER_DAO.update(user);

    return switch (column) {
      case 0 -> user.getRank();
      case 1 -> user.getName();
      case 2 -> user.getWins();
      case 3 -> user.getLosses();
      case 4 -> user.getScore();
      default -> null;
    };
  }

  public void setSorter(TableRowSorter<UserTableModel> sorter) {
    this.sorter = sorter;
  }

}
