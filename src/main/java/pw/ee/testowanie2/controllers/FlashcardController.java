package pw.ee.testowanie2.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.ee.testowanie2.services.FlashcardService;

@RestController
@RequestMapping("/flashcards")
@AllArgsConstructor
public class FlashcardController {
    private final FlashcardService flashcardService;
}
