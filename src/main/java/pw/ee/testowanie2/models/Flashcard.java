package pw.ee.testowanie2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flashcard {
    @Id
    @GeneratedValue
    private Long id;
    private String back;
    private String front;
    private Date createdAt;
    @ManyToOne
    private Set set;
}
