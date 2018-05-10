package com.example.studyreactor;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.Stream;

@Component
public class Learn1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        System.out.println("----------------learn1");

        Flux<String> greetings = Flux.just("hello", "hoi", "hi", "gruetzi");

        // calling operators on a flux is like building a plan of execution for later.
        // it completely declarative that is why people call it "functional"
        greetings
//                    .log()
                .map(String::toUpperCase)
                .log()
                .filter(s -> s.equals("HOI"))
                // these are also operators
                .doOnSubscribe(subscription -> System.out.println("onSubscribe called"))
                .doOnComplete(() -> System.out.println("onComplete called"))

                // can configure the subscriptions to be handled in a background thread using
                .subscribeOn(Schedulers.parallel())
                .limitRate(2)
                // So as a rule, it is a good thing if you can avoid subscribing to a sequence,
                // or at least push that code into a processing layer, and out of the business logic.
                .subscribe();
        // onNext Consumer, onError Consumer, onComplete Runnable
//                    .subscribe(System.out::println, null, () -> System.out.println("Done"));
        // this is what the default .subscribe() gives you
//                    .subscribe(new Subscriber<String>() {
//                        @Override
//                        public void onSubscribe(Subscription subscription) {
//                            subscription.request(Long.MAX_VALUE);
//                        }
//
//                        @Override
//                        public void onNext(String s) {
//                            System.out.println(s);
//                        }
//
//                        @Override
//                        public void onError(Throwable throwable) {
//                        }
//
//                        @Override
//                        public void onComplete() {
//                        }
//                    })

        ;

        Thread.sleep(1000); // needed for Flux.subscribeOn() to wait until the JVM exits


        // similarity to Stream, lazy loading applies here as well
        Stream<String> colours = Stream.of("white", "blue", "green");
        colours.map(String::toUpperCase).forEach(s -> System.out.println(s));




    }
}
