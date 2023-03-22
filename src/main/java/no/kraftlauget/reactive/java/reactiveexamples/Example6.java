package no.kraftlauget.reactive.java.reactiveexamples;

import reactor.core.publisher.Mono;

/**
 * Difficult to read stacktrace when an error occurs
 */
public class Example6 {

    public static void main(String ... args) {
        new Example6.ReactorWithError().runExample();
    }

    static class ReactorWithError {

        void runExample() {
            System.out.println("Reactive programming with Reactor difficult stacktrace:");
            parsePositiveInteger("abc").subscribe(System.out::println); // fails
        }

        public Mono<Integer> parsePositiveInteger(String integerAsString) {
            return Mono.justOrEmpty(integerAsString)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .filter(integer -> integer > 0);
        }
    }
}
