package enigma.todo.service;

import enigma.todo.dto.CategoryDTO;
import enigma.todo.model.Category;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CategoryService {
    Category create(Authentication auth, CategoryDTO categoryDTO);

    List<Category> findAll(Authentication auth);

    Category updateById(Authentication auth, Long id, CategoryDTO categoryDTO);

    void deleteById(Authentication auth, Long id);
}
