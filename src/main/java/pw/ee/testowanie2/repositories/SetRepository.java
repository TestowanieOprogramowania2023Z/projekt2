package pw.ee.testowanie2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.ee.testowanie2.models.Set;

public interface SetRepository extends JpaRepository<Set, Long> {
}
