package no.kraftlauget.reactive.java.functionalexamples;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Basic Predicate example
 *
 * @see java.util.function.Predicate
 */
public class Example1 {

    public static void main(String ... args) {
        concreteClassExecution();
        functionalInterfaceExecution();
        functionalInterfaceExecutionCompact();
    }

    public static class PositiveIntegerPredicate implements Predicate<Integer> {
        @Override
        public boolean test(Integer number) {
            return number > 0;
        }
    }

    static void concreteClassExecution() {
        Predicate<Integer> predicate = new PositiveIntegerPredicate();

        System.out.println("Concrete class execution:");
        System.out.println("predicate.test(10): " + predicate.test(10)); // true
        System.out.println("predicate.test(-1): " + predicate.test(-1)); // false
    }

    static void functionalInterfaceExecution() {
        Predicate<Integer> predicate = number -> number > 0;

        System.out.println("Functional interface execution:");
        System.out.println("predicate.test(10): " + predicate.test(10)); // true
        System.out.println("predicate.test(-1): " + predicate.test(-1)); // false
    }

    static void functionalInterfaceExecutionCompact() {
        Predicate<Integer> predicate = Objects::nonNull;

        System.out.println("Compact functional interface execution:");
        System.out.println("predicate.test(1): " + predicate.test(1)); // true
        System.out.println("predicate.test(null): " + predicate.test(null)); // false
    }

}
