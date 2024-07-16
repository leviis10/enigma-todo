package enigma.todo.service;

import enigma.todo.dto.TodoDetailDTO;
import enigma.todo.dto.response.TodoDetailUpdateContentDTO;
import enigma.todo.model.TodoDetail;
import org.springframework.security.core.Authentication;

public interface TodoDetailService {
    TodoDetail create(Authentication auth, TodoDetailDTO todoDetailDTO);

    TodoDetail findById(Authentication auth, Long todoId, Long todoDetailId);

    TodoDetail updateById(Authentication auth, Long todoId, Long todoDetailId, TodoDetailUpdateContentDTO todoDetailUpdateContentDTO);

    void deleteById(Authentication auth, Long todoId, Long todoDetailId);
}
