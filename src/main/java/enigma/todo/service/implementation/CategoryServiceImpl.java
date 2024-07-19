package enigma.todo.service.implementation;

import enigma.todo.dto.CategoryDTO;
import enigma.todo.model.Category;
import enigma.todo.model.Todo;
import enigma.todo.model.User;
import enigma.todo.repository.CategoryRepository;
import enigma.todo.service.CategoryService;
import enigma.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final TodoService todoService;

    @Override
    public Category create(Authentication auth, CategoryDTO categoryDTO) {
        List<Todo> todos = todoService.findByIdIn(auth, categoryDTO.getTodoIds());
        log.info("todos size is: {}", todos.size());

        return categoryRepository.save(Category.builder()
                .name(categoryDTO.getName())
                .todos(todos)
                .build());
    }

    @Override
    public List<Category> findAll(Authentication auth) {
        return categoryRepository.findAllByUser((User) auth.getPrincipal());
    }

    @Override
    public Category updateById(Authentication auth, Long id, CategoryDTO categoryDTO) {
        Category foundCategory = findById(auth, id);
        foundCategory.setName(categoryDTO.getName());
        return categoryRepository.save(foundCategory);
    }

    @Override
    public void deleteById(Authentication auth, Long id) {
        Category foundCategory = findById(auth, id);
        categoryRepository.delete(foundCategory);
    }

    private Category findById(Authentication auth, Long id) {
        return categoryRepository.findByUserAndId((User) auth.getPrincipal(), id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
