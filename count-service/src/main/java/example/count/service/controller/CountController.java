package example.count.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CountController {
    private static final Logger LOG = LoggerFactory.getLogger(CountController.class);

    @GetMapping("/nums")
    public Flux<Integer> getNumbers(@RequestParam(value = "history", defaultValue = "true") boolean history) {
        return null;
    }
}
