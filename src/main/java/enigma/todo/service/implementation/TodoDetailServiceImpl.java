package enigma.todo.service.implementation;

import enigma.todo.dto.TodoDetailDTO;
import enigma.todo.dto.response.TodoDetailUpdateContentDTO;
import enigma.todo.exceptions.NotFoundException;
import enigma.todo.model.Todo;
import enigma.todo.model.TodoDetail;
import enigma.todo.repository.TodoDetailRepository;
import enigma.todo.service.TodoDetailService;
import enigma.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoDetailServiceImpl implements TodoDetailService {
    private final TodoDetailRepository todoDetailRepository;
    private final TodoService todoService;

    @Override
    public TodoDetail create(Authentication auth, TodoDetailDTO todoDetailDTO) {
        Todo todo = todoService.findById(auth, todoDetailDTO.getTodoId());

        return todoDetailRepository.save(TodoDetail.builder()
                .todo(todo)
                .content(todoDetailDTO.getContent())
                .isCompleted(false)
                .build()
        );
    }

    @Override
    public TodoDetail findById(Authentication auth, Long todoId, Long todoDetailId) {
        Todo todo = todoService.findById(auth, todoId);
        List<TodoDetail> todoDetail = todo.getDetails().stream()
                .filter(todoDetail1 -> todoDetail1.getId().equals(todoDetailId))
                .toList();
        if (todoDetail.isEmpty()) {
            throw new NotFoundException("Todo Detail not found");
        }
        return todoDetail.get(0);
    }

    @Override
    public TodoDetail updateById(Authentication auth, Long todoId, Long todoDetailId, TodoDetailUpdateContentDTO todoDetailUpdateContentDTO) {
        TodoDetail todoDetail = findById(auth, todoId, todoDetailId);
        todoDetail.setContent(todoDetailUpdateContentDTO.getContent());
        return todoDetailRepository.save(todoDetail);
    }

    @Override
    public void deleteById(Authentication auth, Long todoId, Long todoDetailId) {
        TodoDetail todoDetail = findById(auth, todoId, todoDetailId);
        todoDetailRepository.delete(todoDetail);
    }
}
