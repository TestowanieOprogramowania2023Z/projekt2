package pw.ee.testowanie2.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.ee.testowanie2.models.SetDTO;
import pw.ee.testowanie2.services.SetService;

import java.util.List;

@RestController
@RequestMapping("/sets")
@AllArgsConstructor
public class SetController {
    private final SetService setService;

    @GetMapping("/byName/{name}")
    public ResponseEntity<SetDTO> getSetByName(@PathVariable(name = "name") String name) {
        try{
            return ResponseEntity.ok(setService.getSetByName(name).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/byName")
    public ResponseEntity<List<SetDTO>> getSetsOrderByName() {
        try{
            return ResponseEntity.ok(setService.getSetsOrderByName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/byCreatedAt")
    public ResponseEntity<List<SetDTO>> getSetsOrderByCreatedAt() {
        try{
            return ResponseEntity.ok(setService.getSetsOrderByDate());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/byFlashcardsCount")
    public ResponseEntity<List<SetDTO>> getSetsOrderByFlashcards() {
        try{
            return ResponseEntity.ok(setService.getSetsOrderByFlashcardNumber());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
