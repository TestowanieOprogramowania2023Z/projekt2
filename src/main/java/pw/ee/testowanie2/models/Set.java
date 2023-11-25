package pw.ee.testowanie2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "FlashcardSet")
public class Set {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private Date createdAt;
    @OneToMany(mappedBy = "set")
    private List<Flashcard> flashcards;
}
