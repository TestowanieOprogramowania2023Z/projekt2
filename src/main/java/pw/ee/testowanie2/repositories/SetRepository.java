package pw.ee.testowanie2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pw.ee.testowanie2.models.Set;

import java.util.Optional;

public interface SetRepository extends JpaRepository<Set, Long> {
    boolean existsByName(String name);

    Optional<Set> findByName(String name);
}
