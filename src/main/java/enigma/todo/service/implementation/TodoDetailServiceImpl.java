package enigma.todo.service.implementation;

import enigma.todo.model.TodoDetail;
import enigma.todo.repository.TodoDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoDetailServiceImpl {
    private final TodoDetailRepository todoDetailRepository;

    public TodoDetail create() {
        return null;
    }
}
