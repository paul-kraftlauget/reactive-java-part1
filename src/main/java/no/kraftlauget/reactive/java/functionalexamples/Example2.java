package no.kraftlauget.reactive.java.functionalexamples;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Basic Optional example
 *
 * @see Optional
 */
public class Example2 {

    public static void main(String ... args) {
        new NonFunctional().runExample();
        System.out.println();
        new FunctionalWithOptional().runExample();
    }

    static class NonFunctional {

        void runExample() {
            System.out.println("Non-functional programming:");
            System.out.println("parse(\"10\"): " + parse("10")); // 10
            System.out.println("parse(\"   1 \"): " + parse("   1 ")); // 1
            System.out.println("parse(null): " + parse(null)); // null
            System.out.println("parse(\"abc\"): " + parse("abc")); // null
        }

        public Integer parse(String integerAsString) {
            if (integerAsString != null) {
                String trimmed = integerAsString.trim();
                if (StringUtils.isNumeric(trimmed)) {
                    return Integer.parseInt(trimmed);
                }
            }
            return null;
        }


    }

    static class FunctionalWithOptional {

        void runExample() {
            System.out.println("Functional programming with Optional:");
            System.out.println("parse(\"10\"): " + parse("10")); // 10
            System.out.println("parse(\"   1 \"): " + parse("   1 ")); // 1
            System.out.println("parse(null): " + parse(null)); // null
            System.out.println("parse(\"abc\"): " + parse("abc")); // null
        }

        public Integer parse(String integerAsString) {
            return Optional.ofNullable(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt)
                    .orElse(null);
        }
    }


}
