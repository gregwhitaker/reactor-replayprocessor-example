package example.count.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;

import java.time.Duration;

@RestController
public class CountController {
    private static final Logger LOG = LoggerFactory.getLogger(CountController.class);

    private final ReplayProcessor<Integer> replayProcessor = ReplayProcessor.create(Integer.MAX_VALUE);
    private final Flux<Integer> count;

    public CountController() {
        final FluxSink<Integer> sink = replayProcessor.sink(FluxSink.OverflowStrategy.DROP);

        this.count = Flux.range(1, Integer.MAX_VALUE)
                .delayElements(Duration.ofSeconds(1))
                .log();

        this.count.subscribe(sink::next);
    }

    @GetMapping("/nums")
    public Flux<Integer> getNumbers(@RequestParam(value = "history", defaultValue = "true") boolean history) {
        if (history) {
            return replayProcessor;
        } else {
            return count;
        }
    }
}
