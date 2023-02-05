package tictactoe.model.login;


import java.util.List;
import java.util.Optional;

public interface Dao<U> {
  void delete(U u);

  List<User> getAll();

  Optional<User> getByID(int id);

  void save(U u);

  void update(U u);
}
