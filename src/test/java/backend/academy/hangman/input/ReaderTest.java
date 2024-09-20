package backend.academy.hangman.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Тесты класса для чтения пользовательского ввода Reader")
@ExtendWith(MockitoExtension.class)
public class ReaderTest {

    @Mock
    Scanner scanner;

    @InjectMocks
    Reader reader;

    @DisplayName("Тест корректности чтения пользовательского ввода")
    @Test
    public void testRead() {
        String expectedInput = "Игра \"Виселица\"";

        when(scanner.nextLine()).thenReturn(expectedInput);

        String actualInput = reader.read();

        assertEquals(expectedInput, actualInput);
    }
}
