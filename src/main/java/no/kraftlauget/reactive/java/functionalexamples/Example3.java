package no.kraftlauget.reactive.java.functionalexamples;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Basic stream example
 *
 * @see java.util.stream.Stream
 */
public class Example3 {


    public static void main(String ... args) {
        new Example3.NonFunctional().runExample();
        new Example3.FunctionalWithStream().runExample();
    }

    static class NonFunctional {

        void runExample() {

            System.out.println("Non-functional programming:");
            System.out.println(findPositiveNumbers(List.of("10", "-1", "  100 ", "abc"))); // [10, 100]
            System.out.println(findPositiveNumbers(Collections.emptyList())); // []
        }

        public List<Integer> findPositiveNumbers(List<String> list) {
            List<Integer> response = new ArrayList<>();
            for (String item : list) {
                String trimmed = item.trim();
                if (StringUtils.isNumeric(trimmed)) {
                    int integer = Integer.parseInt(trimmed);
                    if (integer > 0) {
                        response.add(integer);
                    }
                }
            }
            return response;
        }


    }

    static class FunctionalWithStream {

        void runExample() {
            System.out.println("Functional programming with stream:");
            System.out.println(findPositiveNumbers(List.of("10", "-1", "  100 ", "abc"))); // [10, 100]
            System.out.println(findPositiveNumbers(Collections.emptyList())); // []
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


}
