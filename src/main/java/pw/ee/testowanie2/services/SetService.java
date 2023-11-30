package pw.ee.testowanie2.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pw.ee.testowanie2.models.Set;
import pw.ee.testowanie2.models.SetDTO;
import pw.ee.testowanie2.repositories.SetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SetService {
    private final SetRepository setRepository;

    public Optional<SetDTO> getSetByName(String name) {
        if (name == null) throw new IllegalArgumentException("Set name cannot be null");
        return setRepository.findByName(name).map(SetDTO::fromSet);
    }

    public List<SetDTO> getSetsOrderByName() {
        List<Set> sets = setRepository.findAllByOrderByName();
        List<SetDTO> setDTOs = new ArrayList<>();
        sets.forEach(s -> setDTOs.add(SetDTO.fromSet(s)));
        return setDTOs;
    }

    public List<SetDTO> getSetsOrderByDate() {
        List<Set> sets = setRepository.findAllByOrderByCreatedAt();
        List<SetDTO> setDTOs = new ArrayList<>();
        sets.forEach(s -> setDTOs.add(SetDTO.fromSet(s)));
        return setDTOs;
    }

    public List<SetDTO> getSetsOrderByFlashcardNumber() {
        List<Set> sets = setRepository.findAllByOrderByFlashcards();
        List<SetDTO> setDTOs = new ArrayList<>();
        sets.forEach(s -> setDTOs.add(SetDTO.fromSet(s)));
        return setDTOs;
    }

    public SetDTO updateSet(Long id, SetDTO setDTO) {
        if(id == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Set id cannot be null");
        if(setDTO == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Set cannot be null");
        Set set = setRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Set not found"));
        set.setName(setDTO.getName());
        return SetDTO.fromSet(setRepository.save(set));
    }
}