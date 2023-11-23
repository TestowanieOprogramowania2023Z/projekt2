package pw.ee.testowanie2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pw.ee.testowanie2.repositories.FlashcardRepository;

@Service
@AllArgsConstructor
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
}
