package enigma.todo.service.implementation;

import enigma.todo.dto.TodoDTO;
import enigma.todo.exceptions.NotFoundException;
import enigma.todo.model.Todo;
import enigma.todo.model.User;
import enigma.todo.repository.TodoRepository;
import enigma.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public Todo create(TodoDTO todoDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return todoRepository.save(Todo.builder()
                .user(user)
                .title(todoDTO.getTitle())
                .build()
        );
    }

    @Override
    public List<Todo> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return todoRepository.findByUser(user);
    }

    @Override
    public Todo findById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return todoRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new NotFoundException("Todo not found"));
    }

    @Override
    public Todo updateById(Long id, TodoDTO todoDTO) {
        Todo foundTodo = findById(id);
        foundTodo.setTitle(todoDTO.getTitle());
        return todoRepository.save(foundTodo);
    }

    @Override
    public void deleteById(Long id) {
        Todo foundTodo = findById(id);
        todoRepository.delete(foundTodo);
    }
}
