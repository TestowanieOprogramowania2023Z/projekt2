package pw.ee.testowanie2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.ee.testowanie2.models.Flashcard;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
