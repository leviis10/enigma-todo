package enigma.todo.service;

import enigma.todo.model.TodoDetail;

import java.util.List;

public interface TodoDetailService {
    TodoDetail create(TodoDetail newTodo);

    List<TodoDetail> findAll();

    TodoDetail findById(Long id);

    TodoDetail updateById(Long id, TodoDetail updatedTodo);

    void deleteById(Long id);
}
