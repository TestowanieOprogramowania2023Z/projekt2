package pw.ee.testowanie2.models;

import jakarta.validation.constraints.Size;

public class FlashcardCreateDTO {
    @Size(min = 1, max = 255)
    private String back;
    @Size(min = 1, max = 255)
    private String front;
    private Long setId;
}
