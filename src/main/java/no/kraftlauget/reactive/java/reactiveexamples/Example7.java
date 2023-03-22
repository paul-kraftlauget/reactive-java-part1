package no.kraftlauget.reactive.java.reactiveexamples;

import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

/**
 * Enable Production-ready global debugging so that stack traces are much easier to read.
 *
 * @see ReactorDebugAgent
 */
public class Example7 {

    public static void main(String ... args) {
        ReactorDebugAgent.init();
        new Example7.ReactorWithError().runExample();
    }

    static class ReactorWithError {

        void runExample() {
            System.out.println("Reactive programming with Reactor readable stacktrace:");
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
