package pw.ee.testowanie2.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private Long flashcardsCount;

    public static SetDTO fromSet(Set set) {
        return SetDTO.builder()
                .id(set.getId())
                .name(set.getName())
                .createdAt(set.getCreatedAt())
                .flashcardsCount((long) set.getFlashcards().size())
                .build();
    }
}
