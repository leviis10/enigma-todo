package enigma.todo.controller.api;

import enigma.todo.dto.TodoDTO;
import enigma.todo.dto.response.Response;
import enigma.todo.dto.response.SuccessResponse;
import enigma.todo.dto.response.TodoDetailUpdateContentDTO;
import enigma.todo.model.Todo;
import enigma.todo.service.TodoDetailService;
import enigma.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final TodoDetailService todoDetailService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Todo>> create(
            Authentication auth,
            @Valid @RequestBody TodoDTO todoDTO
    ) {
        return Response.success(
                todoService.create(auth, todoDTO),
                "Todo created successfully",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Todo>>> findAll(
            Authentication auth
    ) {
        return Response.success(todoService.findAll(auth));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<Todo>> findById(
            Authentication auth,
            @PathVariable Long id
    ) {
        return Response.success(todoService.findById(auth, id));
    }

    @GetMapping("/{todoId}/todo-details/{todoDetailId}")
    public ResponseEntity<?> findTodoDetailById(
            Authentication auth,
            @PathVariable Long todoId,
            @PathVariable Long todoDetailId
    ) {
        return Response.success(todoDetailService.findById(auth, todoId, todoDetailId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
            Authentication auth,
            @PathVariable Long id,
            @Valid @RequestBody TodoDTO todoDTO
    ) {
        return Response.success(
                todoService.updateById(auth, id, todoDTO)
        );
    }

    @PatchMapping("/{todoId}/todo-details/{todoDetailId}")
    public ResponseEntity<?> updateTodoDetailById(
            Authentication auth,
            @PathVariable Long todoId,
            @PathVariable Long todoDetailId,
            @Valid @RequestBody TodoDetailUpdateContentDTO todoDetailUpdateContentDTO
    ) {
        return Response.success(
                todoDetailService.updateById(auth, todoId, todoDetailId, todoDetailUpdateContentDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteById(
            Authentication auth,
            @PathVariable Long id
    ) {
        todoService.deleteById(auth, id);
        return Response.success(
                "Todo deleted successfully"
        );
    }

    @DeleteMapping("/{todoId}/todo-details/{todoDetailId}")
    public ResponseEntity<SuccessResponse<String>> deleteTodoDetailById(
            Authentication auth,
            @PathVariable Long todoId,
            @PathVariable Long todoDetailId
    ) {
        todoDetailService.deleteById(auth, todoId, todoDetailId);
        return Response.success(
                "Todo Detail deleted successfully"
        );
    }
}
