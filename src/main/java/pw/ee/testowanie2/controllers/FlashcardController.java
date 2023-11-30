package pw.ee.testowanie2.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.ee.testowanie2.models.FlashcardDTO;
import pw.ee.testowanie2.models.FlashcardCreateDTO;
import pw.ee.testowanie2.services.FlashcardService;

import java.util.List;

@RestController
@RequestMapping("/flashcards")
@AllArgsConstructor
public class FlashcardController {
    private final FlashcardService flashcardService;

    @GetMapping("/by-set-name/{name}")
    public ResponseEntity<List<FlashcardDTO>> getFlashcardsBySetName(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(flashcardService.getFlashcardsBySetName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardDTO> getFlashcardById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(flashcardService.getFlashcardById(id));
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<FlashcardCreateDTO> createFlashcard(@Valid @RequestBody FlashcardCreateDTO flashcardCreateDTO) {
        try {
            flashcardService.createFlashcard(flashcardCreateDTO);
            return new ResponseEntity<>(flashcardCreateDTO, HttpStatus.CREATED);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
