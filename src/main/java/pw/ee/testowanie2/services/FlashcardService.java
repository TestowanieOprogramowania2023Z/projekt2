package pw.ee.testowanie2.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.FlashcardCreateDTO;
import pw.ee.testowanie2.models.FlashcardDTO;
import pw.ee.testowanie2.repositories.FlashcardRepository;
import pw.ee.testowanie2.repositories.SetRepository;

import java.util.List;

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

    public Flashcard createFlashcard(FlashcardCreateDTO flashcardCreateDTO) throws IllegalAccessException {
        if(!setRepository.existsById(flashcardCreateDTO.getSetId()))
            throw new IllegalAccessException("The set of given id doesnt exist in database");

        Flashcard newFlashcard = Flashcard.builder()
                .back(flashcardCreateDTO.getBack())
                .front(flashcardCreateDTO.getFront())
                .set(setRepository.getById(flashcardCreateDTO.getSetId()))
                .build();

        return flashcardRepository.save(newFlashcard);
    }
}
