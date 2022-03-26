package tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PenGetColorArguments implements ArgumentsProvider {
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception{
        return Stream.of(
                Arguments.of("RED",100),
                Arguments.of("BlUE",100),
                Arguments.of("RED",1000),
                Arguments.of("YELLOW",50)
        );
    }
}
