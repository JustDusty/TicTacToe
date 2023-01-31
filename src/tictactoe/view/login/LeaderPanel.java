package tictactoe.view.login;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import tictactoe.application.GameConstants;
import tictactoe.application.Util;
import tictactoe.controllers.LoginListener;
import tictactoe.model.User;
import tictactoe.model.UserTableModel;

public class LeaderPanel extends JPanel {
  private static final long serialVersionUID = 1L;



  private UserTableModel model;
  private JTable userTable;
  private transient TableRowSorter<UserTableModel> sorter;

  private JTextField txtUser;
  private JButton btnPrevious;
  private JButton btnConfirm;
  private JButton btnQuit;


  private transient List<LoginListener> listeners = new ArrayList<>();

  private boolean isBeingModified = false;


  public LeaderPanel(UserTableModel model) {

    JPanel mainPanel = new JPanel();

    mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(2, 5, 2, 0);

    JLabel labelFilter = new JLabel();
    labelFilter.setText("Filter: ");
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.WEST;
    mainPanel.add(labelFilter, c);

    txtUser = new JTextField();
    c.insets = new Insets(2, 0, 2, 0);
    c.gridx = 1;
    c.gridy = 0;
    c.gridwidth = 3;
    c.weightx = 200;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.WEST;
    mainPanel.add(txtUser, c);

    JPanel userTablePanel = createUserTablePanel(model);
    c.insets = new Insets(0, 0, 0, 0);
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 4;
    c.weightx = 0;
    c.fill = GridBagConstraints.BOTH;
    mainPanel.add(userTablePanel, c);

    btnConfirm = new JButton("Confirm");
    c.insets = new Insets(2, 0, 2, 0);
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 1;
    mainPanel.add(btnConfirm, c);

    Component strut = Box.createHorizontalStrut(150);
    c.gridx = 1;
    c.gridy = 3;
    mainPanel.add(strut, c);

    btnPrevious = new JButton("Go Back");
    c.gridx = 2;
    c.gridy = 3;
    c.anchor = GridBagConstraints.EAST;
    mainPanel.add(btnPrevious, c);

    btnQuit = new JButton("Quit");
    c.gridx = 3;
    c.gridy = 3;
    c.anchor = GridBagConstraints.WEST;
    mainPanel.add(btnQuit, c);

    add(mainPanel);
    Util.setFontStyle(mainPanel);

    setTableFilterListeners();

  }

  public void addLoginListener(LoginListener listener) {
    listeners.add(listener);
  }

  public JPanel createUserTablePanel(UserTableModel model) {
    JPanel userTablePanel = new JPanel();
    this.model = model;
    userTable = new JTable(model);
    userTable.setShowGrid(false);
    userTable.setShowHorizontalLines(false);
    userTable.setShowVerticalLines(false);
    userTable.setRowMargin(0);
    userTable.setIntercellSpacing(new Dimension(0, 0));
    userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    userTable.setPreferredScrollableViewportSize(new Dimension(370, 400));
    userTable.setFillsViewportHeight(true);



    sorter = new TableRowSorter<>(model);
    sorter.setComparator(0, Comparator.naturalOrder());
    userTable.setRowSorter(sorter);

    JScrollPane scrollPane = new JScrollPane(userTable);
    scrollPane.putClientProperty("JScrollBar.showButtons", true);
    userTablePanel.add(scrollPane);

    return userTablePanel;
  }

  public void fireUserSelectionEvent(User user) {
    for (LoginListener listener : listeners)
      listener.userSelected(user);
  }

  public JButton getBtnConfirm() {
    return btnConfirm;
  }

  public JButton getBtnPrevious() {
    return btnPrevious;
  }

  public JButton getBtnQuit() {
    return btnQuit;
  }


  @Override
  public Dimension getPreferredSize() {
    return Util.largePanelSize;
  }



  public JTable getUserTable() {
    return userTable;
  }

  public UserTableModel getUserTableModel() {
    return model;
  }

  public JTextField getUserTextField() {
    return txtUser;
  }

  public void newFilter() {
    userTable.clearSelection();
    RowFilter<UserTableModel, Object> filter = null;
    try {
      filter = RowFilter.regexFilter(txtUser.getText(), 0);
    } catch (java.util.regex.PatternSyntaxException e) {
      return;
    }
    sorter.setRowFilter(filter);
  }

  public void registerActionListener(ActionListener listener) {
    btnConfirm.setActionCommand(GameConstants.CONFIRM_USER);
    btnPrevious.setActionCommand(GameConstants.PREVIOUS);
    btnQuit.setActionCommand(GameConstants.QUIT);

    btnConfirm.addActionListener(listener);
    btnQuit.addActionListener(listener);
    btnPrevious.addActionListener(listener);


    btnConfirm.addActionListener(e -> {
      User selectedUser;
      try {
        int row = userTable.getSelectedRow();
        selectedUser = getUserTableModel().getUserDataAt(row);
        fireUserSelectionEvent(selectedUser);
      } catch (IndexOutOfBoundsException ex) {
        ex.printStackTrace();
      }
    });


  }

  public void setTableFilterListeners() {
    userTable.getSelectionModel().addListSelectionListener(e -> EventQueue.invokeLater(() -> {
      isBeingModified = true;
      int row = userTable.getSelectedRow();
      if (row < 0)
        txtUser.setText("");
      else {
        String name = userTable.getValueAt(row, 0).toString();
        txtUser.setText(name);
      }
      isBeingModified = false;
    }));

    txtUser.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        if (!isBeingModified)
          newFilter();
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        if (!isBeingModified)
          newFilter();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        if (!isBeingModified)
          newFilter();
      }

    });
    txtUser.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        txtUser.setText("");
      }
    });

  }

  public void setUserTableModel(UserTableModel model) {
    this.model = model;
  }



}
