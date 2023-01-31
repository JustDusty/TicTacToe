package tictactoe.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  private String name;
  private int wins;
  private int losses;



  private PropertyChangeSupport pcs = new PropertyChangeSupport(this);


  public User(String name) {
    this.name = name;
    this.wins = 0;
    this.losses = 0;

  }


  public int getLosses() {
    return losses;
  }



  public String getName() {
    return name;
  }

  public int getScore() {
    if (losses == 0)
      return wins;
    else
      return wins / losses;
  }

  public int getWins() {
    return wins;
  }

  public void incrementLosses() {
    int old = losses;
    losses = old + 1;
    pcs.firePropertyChange("roundLoss", old, old + 1);
  }

  public void incrementWins() {
    int old = wins;
    wins = old + 1;
    pcs.firePropertyChange("roundWin", old, old + 1);
  }

  public void registerPropertyChangeListener(PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  public void setLosses(int newLoss) {
    int old = losses;
    losses = newLoss;
    pcs.firePropertyChange("lossesUser", old, newLoss);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setWins(int newWin) {
    int old = wins;
    wins = newWin;
    pcs.firePropertyChange("winsUser", old, newWin);
  }



  @Override
  public String toString() {
    return name;
  }



}
