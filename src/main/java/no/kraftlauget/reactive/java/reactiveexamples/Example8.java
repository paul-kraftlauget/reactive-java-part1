package no.kraftlauget.reactive.java.reactiveexamples;

import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

/**
 * Checkpoints
 *
 * @see ReactorDebugAgent
 */
public class Example8 {

    public static void main(String ... args) {
        ReactorDebugAgent.init();
        new ReactorCheckpoint().runExample();
    }

    static class ReactorCheckpoint {

        void runExample() {
            System.out.println("Reactive programming with Reactor readable stacktrace and checkpoint:");
            parsePositiveInteger("abc").subscribe(System.out::println); // fails
        }

        public Mono<Integer> parsePositiveInteger(String integerAsString) {
            return Mono.justOrEmpty(integerAsString)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .filter(integer -> integer > 0)
                    .checkpoint("Handling " + integerAsString);
        }
    }
}
