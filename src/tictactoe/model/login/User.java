package tictactoe.model.login;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class User {
  private int id;
  private String name;
  private int wins;
  private int rank;
  private int losses;



  private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  public User(int id, String name) {
    this.id = id;
    this.name = name;
    this.wins = 0;
    this.losses = 0;
  }

  public User(int id, String username, int wins, int losses) {
    this.id = id;
    this.name = username;
    this.wins = wins;
    this.losses = losses;
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof User u))
      return false;
    return (this.id == u.id && this.name == u.name && this.wins == u.wins
        && this.losses == u.losses);
  }

  public int getID() {
    return id;
  }


  public int getLosses() {
    return losses;
  }



  public String getName() {
    return name;
  }

  public int getRank() {
    return this.rank;

  }

  public double getScore() {
    if (losses == 0)

      return wins / 1.0;
    else
      return (double) wins / losses;
  }

  public int getWins() {
    return wins;
  }

  @Override
  public int hashCode() {
    int h = 13;
    h = 43 * h + this.id;
    h = 43 * h + this.name.hashCode();
    h = 43 * h + this.wins;
    h = 43 * h + this.losses;
    return h;
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

  public void setID(int id) {
    this.id = id;
  }

  public void setLosses(int newLoss) {
    int old = losses;
    losses = newLoss;
    pcs.firePropertyChange("lossesUser", old, newLoss);
  }



  public void setName(String name) {
    this.name = name;
  }

  public void setRank(int rank) {
    this.rank = rank;
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
