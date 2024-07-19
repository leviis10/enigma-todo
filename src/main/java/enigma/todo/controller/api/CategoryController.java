package enigma.todo.controller.api;

import enigma.todo.dto.CategoryDTO;
import enigma.todo.dto.response.Response;
import enigma.todo.dto.response.SuccessResponse;
import enigma.todo.model.Category;
import enigma.todo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Category>> create(
            Authentication auth,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        return Response.success(
                categoryService.create(auth, categoryDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Category>>> findAll(
            Authentication auth
    ) {
        return Response.success(categoryService.findAll(auth));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<Category>> updateById(
            Authentication auth,
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        return Response.success(categoryService.updateById(auth, id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteById(
            Authentication auth,
            @PathVariable Long id
    ) {
        categoryService.deleteById(auth, id);
        return Response.success("Category deleted successfully");
    }
}
