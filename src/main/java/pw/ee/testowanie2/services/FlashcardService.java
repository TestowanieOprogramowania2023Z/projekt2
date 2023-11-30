package pw.ee.testowanie2.services;

import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.FlashcardCreateDTO;
import pw.ee.testowanie2.models.FlashcardDTO;
import pw.ee.testowanie2.repositories.FlashcardRepository;

import java.util.List;
import pw.ee.testowanie2.repositories.SetRepository;

@Service
@AllArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final SetRepository setRepository;
    public List<FlashcardDTO> getFlashcardsBySetName(String name) {
        if (name == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Set name cannot be null");
        List<Flashcard> flashcards = flashcardRepository.findBySetName(name);
        return flashcards.stream().map(FlashcardDTO::fromFlashcard).toList();
    }

    public FlashcardDTO getFlashcardById(Long id) {
        if (id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flashcard id cannot be null");
        Flashcard flashcard = flashcardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found"));
        return FlashcardDTO.fromFlashcard(flashcard);
    }

    public void deleteById(Long id) {
        if (!flashcardRepository.existsById(id)) {
            throw new NoSuchElementException("Flashcard with provided id doesn't exist");
        }
        flashcardRepository.deleteById(id);
    }

    public void updateById(Long id, FlashcardCreateDTO flashcardCreateDTO) {
        Flashcard flashcard = flashcardRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Flashcard with provided id doesn't exist"));
        flashcard.setSet(setRepository.findById(flashcardCreateDTO.getSetId())
            .orElseThrow(() -> new NoSuchElementException("Set with provided id doesn't exist")));
        flashcard.setBack(flashcardCreateDTO.getBack());
        flashcard.setFront(flashcardCreateDTO.getFront());
        flashcardRepository.save(flashcard);
    }


}
