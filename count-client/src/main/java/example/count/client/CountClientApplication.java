package example.count.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CountDownLatch;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.populateCommand;

@SpringBootApplication
public class CountClientApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CountClientApplication.class);

    public static void main(String... args) {
        SpringApplication app = new SpringApplication(CountClientApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Component
    class Runner implements CommandLineRunner {

        @Override
        public void run(String... args) throws Exception {
            CommandLineArgs params = populateCommand(new CommandLineArgs(), args);

            WebClient client = WebClient.create("http://localhost:8080");
            CountDownLatch latch = new CountDownLatch(1);

            if (params.history) {
                LOG.info("Getting the count with history...");

                client.get()
                        .uri("/nums?history=true")
                        .retrieve()
                        .bodyToFlux(Integer.class)
                        .doOnError(throwable -> LOG.error("Error occurred retrieving count with history.", throwable))
                        .subscribe(num -> {
                            LOG.info("Count: {}", num);
                        });
            } else {
                LOG.info("Getting the count without history...");

                client.get()
                        .uri("/nums")
                        .retrieve()
                        .bodyToFlux(Integer.class)
                        .doOnError(throwable -> LOG.error("Error occurred retrieving count without history.", throwable))
                        .subscribe(num -> {
                            LOG.info("Count: {}", num);
                        });
            }

            latch.await();
        }
    }

    static class CommandLineArgs {

        @Option(names = "--history", defaultValue = "true", description = "retrieve all count history")
        private boolean history;
    }
}
