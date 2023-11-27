package pw.ee.testowanie2.models;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlashcardCreateDTO {
    @Size(min = 1, max = 255)
    private String back;
    @Size(min = 1, max = 255)
    private String front;
    private Long setId;
}
