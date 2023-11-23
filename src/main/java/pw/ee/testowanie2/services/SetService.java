package pw.ee.testowanie2.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pw.ee.testowanie2.repositories.SetRepository;

@Service
@AllArgsConstructor
public class SetService {
    private final SetRepository setRepository;
}
