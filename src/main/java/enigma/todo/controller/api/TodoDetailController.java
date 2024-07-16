package enigma.todo.controller.api;

import enigma.todo.dto.TodoDetailDTO;
import enigma.todo.dto.response.Response;
import enigma.todo.dto.response.SuccessResponse;
import enigma.todo.model.TodoDetail;
import enigma.todo.service.TodoDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo-details")
@RequiredArgsConstructor
public class TodoDetailController {
    private final TodoDetailService todoDetailService;

    @PostMapping
    public ResponseEntity<SuccessResponse<TodoDetail>> create(
            Authentication auth,
            @Valid @RequestBody TodoDetailDTO todoDetailDTO
    ) {
        return Response.success(todoDetailService.create(auth, todoDetailDTO));
    }
}
