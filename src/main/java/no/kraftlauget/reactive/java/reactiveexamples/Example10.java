package no.kraftlauget.reactive.java.reactiveexamples;

import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Putting it all together
 */
public class Example10 {

    public static void main(String ... args) {
        new MonoFluxMapFlatMapFilterExample().runExample();
    }

    static class MonoFluxMapFlatMapFilterExample {

        void runExample() {
            readIntsArray("10", "  1  ", null, "abc", "")
                    .subscribe(System.out::println);
        }

        public Flux<Integer> readIntsArray(String ... items) {
            return Mono.justOrEmpty(items)
                    .flatMap(this::removeNulls)
                    .flatMapMany(Flux::fromStream)
                    .flatMap(this::parse);
        }

        public Mono<Stream<String>> removeNulls(String[] items) {
            return Mono.just(items)
                    .map(Stream::of)
                    .map(stream -> stream.filter(Objects::nonNull));
        }

        public Mono<Integer> parse(String integerAsString) {
            return Mono.justOrEmpty(integerAsString)
                    .map(String::trim)
                    .filter(StringUtils::isNumeric)
                    .map(Integer::parseInt);
        }
    }

}
