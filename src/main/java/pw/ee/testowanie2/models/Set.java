package pw.ee.testowanie2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class Set {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "set")
    private List<Flashcard> flashcards;
}
