package enigma.todo.repository;

import enigma.todo.model.Category;
import enigma.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query("SELECT DISTINCT c FROM Category c JOIN c.todos t WHERE t.user = :user")
    List<Category> findAllByUser(@Param("user") User user);

    @Query("SELECT c FROM Category c JOIN c.todos t WHERE c.id = :id AND t.user = :user")
    Optional<Category> findByUserAndId(@Param("user") User user, @Param("id") Long id);
}
