package pw.ee.testowanie2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.FlashcardDTO;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findBySetName(String name);
}
