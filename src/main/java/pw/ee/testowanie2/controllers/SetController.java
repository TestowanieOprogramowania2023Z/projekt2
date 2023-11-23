package pw.ee.testowanie2.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pw.ee.testowanie2.services.SetService;

@RestController
@RequestMapping("/sets")
@AllArgsConstructor
public class SetController {
    private final SetService setService;
}
