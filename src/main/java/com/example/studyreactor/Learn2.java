package com.example.studyreactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class Learn2 implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(Learn2.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------learn2");

        Flux.just("red", "white", "blue")
                .log()
                // switching the processing of the individual items into separate publishers, and for each of those publishers
                // ask for the result in a background thread
                .flatMap(value -> Mono.just(value.toUpperCase()).subscribeOn(Schedulers.parallel()), 2)
                .subscribe(value -> log.info("Consumed: " + value));

        System.out.println("------------------------");

        // Extractors: A good rule of thumb is "never call an extractor" unless you are testing
        // Mono.block
        // Flux.toFuture
        // Flux.toStream

        // Subscribing where the Publisher is created is for examples only.
        // There's almost no real world justification for that because you're essentially blocking. With a reactive web server like Netty,
        // you never need to write subscription code yourself, you can just return the Publisher from your controller.


    }
}
