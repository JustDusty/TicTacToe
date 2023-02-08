package tictactoe.model.login;


import java.util.List;
import java.util.Optional;

/**
 * 
 * Data Access Object parent interface for this project. Currently only a user specific DAO is being
 * implemented.
 *
 * @param <U>
 */
public interface Dao<U> {
  /**
   * Deletes an object from the Database
   * 
   * @param u
   */
  void delete(U u);

  /**
   * retrieves all objects from the database as a list
   * 
   * @return
   */
  List<U> getAll();

  /**
   * Retrieves one object from the database by it's id attribute as an optional to account for the
   * possibility of a null result and handle it accordingly
   * 
   * @param id
   * @return the Optional object
   */
  Optional<U> getByID(int id);


  /**
   * Saves an object into the database
   * 
   * @param u
   */
  void save(U u);

  /**
   * Method that takes an instance of an object and updates its attributes using existing values in
   * the database. Made for synchronizing data between database and app.
   * 
   * @param u
   */
  void update(U u);
}
