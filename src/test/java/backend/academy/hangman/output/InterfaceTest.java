package backend.academy.hangman.output;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты класса для вывода текста в консоль Interface")
public class InterfaceTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @DisplayName("Тест вывода текста с новой строки")
    @Test
    public void testPrintWithNewLine() {
        Interface.print("Игра \"Виселица\"", true);

        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("Игра \"Виселица\"");
    }

    @DisplayName("Тест вывода текста с текущей строки")
    @Test
    public void testPrintWithoutNewLine() {
        Interface.print("Игра ", false);
        Interface.print("\"Виселица\"", false);

        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("Игра \"Виселица\"");
    }
}
