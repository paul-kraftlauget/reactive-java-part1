package no.kraftlauget.reactive.java.reactiveexamples;

import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * First reactive examples, with blocking
 */
public class Example4 {

    public static void main(String ... args) {
        new Example4.Functional().runExample();
        new Example4.ReactorBlocking().runExample();
    }


    static class Functional {

        void runExample() {
            System.out.println("Functional programming (copied from example 2 and 3):");
            System.out.println(parse("10")); // 10
            System.out.println(parse("   1 ")); // 1
            System.out.println(parse(null)); // null
            System.out.println(parse("abc")); // null
            System.out.println(findPositiveNumbers(List.of("10", "-1", "  100 ", "abc"))); // [10, 100]
            System.out.println(findPositiveNumbers(Collections.emptyList())); // []
        }

        public Integer parse(String integerAsString) {
            return Optional.ofNullable(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .orElse(null);
        }
        public List<Integer> findPositiveNumbers(List<String> list) {
            return list.stream()
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .filter(integer -> integer > 0)
                    .toList();
        }
    }

    static class ReactorBlocking {

        void runExample() {
            System.out.println("Reactive programming with Reactor:");
            System.out.println(parse("10")); // 10
            System.out.println(parse("   1 ")); // 1
            System.out.println(parse(null)); // null
            System.out.println(parse("abc")); // null
            System.out.println(findPositiveNumbers(List.of("10", "-1", "  100 ", "abc"))); // [10, 100]
            System.out.println(findPositiveNumbers(Collections.emptyList())); // []
        }

        public Integer parse(String integerAsString) {
            return Mono.justOrEmpty(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .block();
        }

        public List<Integer> findPositiveNumbers(List<String> list) {
            return Flux.fromIterable(list)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .filter(integer -> integer > 0)
                    .collectList()
                    .block();
        }
    }
}
