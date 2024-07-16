package enigma.todo.repository;

import enigma.todo.model.TodoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoDetailRepository extends JpaRepository<TodoDetail, Long> {
}
