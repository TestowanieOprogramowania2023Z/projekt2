package pw.ee.testowanie2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pw.ee.testowanie2.models.Flashcard;
import pw.ee.testowanie2.models.FlashcardCreateDTO;
import pw.ee.testowanie2.repositories.FlashcardRepository;
import pw.ee.testowanie2.repositories.SetRepository;

@Service
@AllArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;

    private final SetRepository setRepository;
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
