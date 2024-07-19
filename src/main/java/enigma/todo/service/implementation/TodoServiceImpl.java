package enigma.todo.service.implementation;

import enigma.todo.dto.TodoDTO;
import enigma.todo.exceptions.NotFoundException;
import enigma.todo.model.Todo;
import enigma.todo.model.User;
import enigma.todo.repository.TodoRepository;
import enigma.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public Todo create(Authentication auth, TodoDTO todoDTO) {
        return todoRepository.save(Todo.builder()
                .user(getUser(auth))
                .title(todoDTO.getTitle())
                .build()
        );
    }

    @Override
    public List<Todo> findAll(Authentication auth) {
        return todoRepository.findByUser(getUser(auth));
    }

    @Override
    public List<Todo> findByIdIn(Authentication auth, List<Long> ids) {
        return todoRepository.findByUserAndIdIn(getUser(auth), ids);
    }

    @Override
    public Todo findById(Authentication auth, Long id) {
        return todoRepository.findByUserAndId(getUser(auth), id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));
    }

    @Override
    public Todo updateById(Authentication auth, Long id, TodoDTO todoDTO) {
        Todo foundTodo = findById(auth, id);
        foundTodo.setTitle(todoDTO.getTitle());
        return todoRepository.save(foundTodo);
    }

    @Override
    public void deleteById(Authentication auth, Long id) {
        Todo foundTodo = findById(auth, id);
        todoRepository.delete(foundTodo);
    }

    private User getUser(Authentication auth) {
        return (User) auth.getPrincipal();
    }
}
