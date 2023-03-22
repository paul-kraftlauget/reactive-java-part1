package no.kraftlauget.reactive.java.functionalexamples;

import org.apache.commons.lang3.StringUtils;
import reactor.tools.agent.ReactorDebugAgent;

import java.util.Optional;

/**
 * Optional map VS flatMap
 */
public class Example9 {

    public static void main(String ... args) {
        ReactorDebugAgent.init();
        new UsingMap().runExample();
        new UsingMapInsteadOfFlatMap().runExample();
        new UsingFlatMap().runExample();
    }

    static class UsingMap {

        void runExample() {
            System.out.println("Optional.map example:");
            runExample("10");
            runExample("   1 ");
            runExample(null); // Does not execute, already 'empty'
            runExample("abc"); // Does not execute, changes to 'empty'
        }

        void runExample(String data) {
            Optional.ofNullable(data)
                    .map(this::parse)
                    .ifPresent(System.out::println);
        }

        public Integer parse(String integerAsString) {
            return Optional.ofNullable(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .orElse(null);
        }
    }

    static class UsingMapInsteadOfFlatMap {

        void runExample() {
            System.out.println("Optional.map when probably would want flatMap:");
            runExample("10"); // Optional[10]
            runExample("   1 "); // Optional[1]
            runExample(null); // Does not execute, already 'empty'
            runExample("abc"); // Optional.empty
        }

        void runExample(String data) {
            Optional.ofNullable(data)
                    .map(this::parse)
                    .ifPresent(System.out::println);
        }

        public Optional<Integer> parse(String integerAsString) {
            return Optional.ofNullable(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt);
        }
    }

    static class UsingFlatMap {

        void runExample() {
            System.out.println("Optional.flatMap example:");
            runExample("10");
            runExample("   1 ");
            runExample(null); // Does not execute, already 'empty'
            runExample("abc"); // Does not execute, changes to 'empty'
        }

        void runExample(String data) {
            Optional.ofNullable(data)
                    .flatMap(this::parse)
                    .ifPresent(System.out::println);
        }

        public Optional<Integer> parse(String integerAsString) {
            return Optional.ofNullable(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt);
        }
    }

}
