package tictactoe.model.login;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class User {
  private int id;
  private int rank;
  private String name;
  private int wins;

  private int losses;



  private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

  /**
   * 
   * @param id : the randomly generated UUID as a integer.
   * @param name : the username
   */
  public User(int id, String name) {
    this.id = id;
    this.name = name;
    this.wins = 0;
    this.losses = 0;
  }

  /**
   * 
   * @param id : the randomly generated UUID as a integer.
   * @param username : the username
   * @param wins : the number of wins for this user
   * @param losses : the number of losses for this user
   */
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


  /**
   * a randomly generated UUID as an integer value using the {@link Util#generateUniqueId()
   * generateUniqueId()} method
   * 
   * @return id: user's id.
   */
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
    return rank;
  }


  /**
   * calculates user score based on wins and losses
   * 
   * @return the quotient of wins and losses as a double type, or the number of wins if the losses
   *         are 0.
   */
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

  /**
   * Increments the user's number of losses by 1.
   * 
   * Notifies all property change listeners of the change.
   */
  public void incrementLosses() {
    int old = losses;
    losses = old + 1;
    pcs.firePropertyChange("roundLoss", old, old + 1);
  }

  /**
   * Increments the user's number of wins by 1.
   * 
   * Notifies all property change listeners of the change.
   */
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

  /**
   * updates the number of the user's losses by the new value.
   * 
   * Notifies all property change listeners of the change.
   * 
   * @param newLoss
   */
  public void setLosses(int newLoss) {
    int old = losses;
    losses = newLoss;
    pcs.firePropertyChange("lossesUser", old, newLoss);
  }


  /**
   * updates the username.
   * 
   * @param name : the user's new username
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * updates the user's rank in the leaderboard. the rank is calculated from the score using an SQL
   * Query. {@link UserDaoImpl#update(User) UserDaoImpl.update}
   * 
   * @param rank : integer greater or equal to 1.
   */
  public void setRank(int rank) {
    this.rank = rank;

  }


  /**
   * updates the number of the user's wins by the new value.
   * 
   * Notifies all property change listeners of the change.
   * 
   * @param newLoss
   */
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
