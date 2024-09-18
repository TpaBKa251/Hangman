package backend.academy.hangman.output;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void testPrintWithNewLine() {
        Interface.print("Игра \"Виселица\"", true);

        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("Игра \"Виселица\"");
    }

    @Test
    public void testPrintWithoutNewLine() {
        Interface.print("Игра ", false);
        Interface.print("\"Виселица\"", false);

        String output = outputStream.toString().trim();
        assertThat(output).isEqualTo("Игра \"Виселица\"");
    }
}
