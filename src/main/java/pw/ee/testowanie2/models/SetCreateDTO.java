package pw.ee.testowanie2.models;

import jakarta.validation.constraints.Size;

public class SetCreateDTO {
    @Size(min = 1, max = 255)
    private String name;
}
