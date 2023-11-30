package pw.ee.testowanie2.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.ee.testowanie2.models.SetCreateDTO;
import pw.ee.testowanie2.models.SetDTO;
import pw.ee.testowanie2.services.SetService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sets")
@AllArgsConstructor
public class SetController {
    private final SetService setService;

    @GetMapping("/{id}")
    public ResponseEntity<SetDTO> getSetById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(setService.getSetById(id));
    }
    
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

    @PutMapping("/{id}")
    public ResponseEntity<SetDTO> updateSet(@PathVariable(name = "id") Long id, @RequestBody SetDTO setDTO) {
        return ResponseEntity.ok(setService.updateSet(id, setDTO));
    }
    
    @PostMapping("")
    public ResponseEntity<Long> createSet(@RequestBody @Valid SetCreateDTO setDTO) {
        return ResponseEntity.created(URI.create(setService.createSet(setDTO).toString())).build();
    }
}
