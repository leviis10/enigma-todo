package enigma.todo.service;

import enigma.todo.dto.TodoDTO;
import enigma.todo.model.Todo;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TodoService {
    Todo create(Authentication auth, TodoDTO todoDTO);

    List<Todo> findAll(Authentication auth);

    Todo findById(Authentication auth, Long id);

    Todo updateById(Authentication auth, Long id, TodoDTO todoDTO);

    void deleteById(Authentication auth, Long id);
}
