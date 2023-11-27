package pw.ee.testowanie2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pw.ee.testowanie2.models.Set;
import java.util.List;
import java.util.Optional;

public interface SetRepository extends JpaRepository<Set, Long> {
    Optional<Set> findByName(String name);
    List<Set> findAllByOrderByName();
    List<Set> findAllByOrderByCreatedAt();
    @Query("SELECT s FROM Set s LEFT JOIN s.flashcards f GROUP BY s ORDER BY COUNT(f) DESC")
    List<Set> findAllByOrderByFlashcards();
    boolean existsByName(String name);
}
