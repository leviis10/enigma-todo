package enigma.todo.service;

import enigma.todo.dto.TodoDTO;
import enigma.todo.model.Todo;

import java.util.List;

public interface TodoService {
    Todo create(TodoDTO todoDTO);

    List<Todo> findAll();

    Todo findById(Long id);

    Todo updateById(Long id, TodoDTO todoDTO);

    void deleteById(Long id);
}
