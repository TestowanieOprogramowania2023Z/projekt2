package pw.ee.testowanie2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @CreationTimestamp
    private Date createdAt;
    @OneToMany(mappedBy = "set", fetch=FetchType.EAGER)
    private List<Flashcard> flashcards;
}
