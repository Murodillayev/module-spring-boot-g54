package uz.pdp.todo;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class TestAddCasesProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {




        return Stream.of(
                Arguments.of(1.0, 2.0, 3.0),
                Arguments.of(5.5, 4.5, 10.0),
                Arguments.of(-1.0, 1.0, 0.0),
                Arguments.of(0.0, 0.0, 0.0),
                Arguments.of(3.14, 0.86, 4.0),
                Arguments.of(-2.5, -3.5, -6.0)
        );    }
}
