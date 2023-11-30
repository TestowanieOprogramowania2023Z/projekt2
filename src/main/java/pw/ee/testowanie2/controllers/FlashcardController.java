package pw.ee.testowanie2.controllers;

import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.ee.testowanie2.models.FlashcardCreateDTO;
import pw.ee.testowanie2.models.FlashcardDTO;
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
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id,
        @RequestBody FlashcardCreateDTO flashcardCreateDTO) {
        try {
            flashcardService.updateById(id, flashcardCreateDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Successfully updated a flashcard");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            flashcardService.deleteById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Successfully deleted a flashcard");
    }

}
