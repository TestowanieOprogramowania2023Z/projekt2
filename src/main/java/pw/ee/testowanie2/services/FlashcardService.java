package pw.ee.testowanie2.services;

import java.util.NoSuchElementException;
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
