package enigma.todo.controller.api;

import enigma.todo.dto.TodoDTO;
import enigma.todo.dto.response.Response;
import enigma.todo.dto.response.SuccessResponse;
import enigma.todo.model.Todo;
import enigma.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Todo>> create(
            @Valid @RequestBody TodoDTO todoDTO
    ) {
        return Response.success(
                todoService.create(todoDTO),
                "Todo created successfully",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Todo>>> findAll() {
        return Response.success(todoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<Todo>> findById(
            @PathVariable Long id
    ) {
        return Response.success(todoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
            @PathVariable Long id,
            @Valid @RequestBody TodoDTO todoDTO
    ) {
        return Response.success(
                todoService.updateById(id, todoDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteById(
            @PathVariable Long id
    ) {
        todoService.deleteById(id);
        return Response.success(
                "Todo deleted successfully"
        );
    }
}
