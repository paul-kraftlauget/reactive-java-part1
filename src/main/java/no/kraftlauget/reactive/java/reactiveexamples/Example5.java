package no.kraftlauget.reactive.java.reactiveexamples;

import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Methods with optional/stream or mono/flux return objects
 */
public class Example5 {

    public static void main(String ... args) {
        new Example5.FunctionalWithReturn().runExample();
        System.out.println();
        new Example5.ReactorNonblocking().runExample();
    }


    static class FunctionalWithReturn {

        void runExample() {
            System.out.println("Functional programming (optional/stream return objects):");
            parse("10").ifPresent(System.out::println); // 10
            parse("   1 ").ifPresent(System.out::println); // 1
            parse(null).ifPresent(System.out::println); // "not present - not executed"
            parse("abc").ifPresent(System.out::println); // "not present - not executed"
            findPositiveNumbers(List.of("10", "-1", "  100 ", "abc")).forEach(System.out::println); // 10, 100
            findPositiveNumbers(Collections.emptyList()).forEach(System.out::println); // no items in list, not executed
        }

        public Optional<Integer> parse(String integerAsString) {
            return Optional.ofNullable(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt);
        }
        public Stream<Integer> findPositiveNumbers(List<String> list) {
            return list.stream()
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .filter(integer -> integer > 0);
        }
    }

    static class ReactorNonblocking {

        void runExample() {
            System.out.println("Reactive programming with Reactor with Mono and Flux return values:");
            parse("10").subscribe(System.out::println); // 10
            parse("   1 ").subscribe(System.out::println); // 1
            parse(null).subscribe(System.out::println); // "empty - not executed"
            parse("abc").subscribe(System.out::println); // "empty - not executed"
            findPositiveNumbers(List.of("10", "-1", "  100 ", "abc")).subscribe(System.out::println); // 10, 100
            findPositiveNumbers(Collections.emptyList()).subscribe(System.out::println); // not executed, empty flux
        }

        public Mono<Integer> parse(String integerAsString) {
            return Mono.justOrEmpty(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt);
        }

        public Flux<Integer> findPositiveNumbers(List<String> list) {
            return Flux.fromIterable(list)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .filter(integer -> integer > 0);
        }
    }
}
