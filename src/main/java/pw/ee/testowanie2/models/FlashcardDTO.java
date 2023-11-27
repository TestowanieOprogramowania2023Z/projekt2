package pw.ee.testowanie2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlashcardDTO {
    private Long id;
    private String back;
    private String front;
    private Date createdAt;
    private Long setId;
}
