package enigma.todo.repository;

import enigma.todo.model.Todo;
import enigma.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);

    Optional<Todo> findByUserAndId(User user, Long id);

    List<Todo> findByUserAndIdIn(User user, List<Long> ids);
}
