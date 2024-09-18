package backend.academy.hangman.output;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisualiserHangmanTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut = System.out;

    private VisualizerHangman visualizerHangman;

    @BeforeEach
    public void setUp() {
        visualizerHangman = new VisualizerHangman();

        outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testPrintHangmanZeroMistake() {
        int mistake = 0;
        String expectedHangman = "";

        visualizerHangman.print(mistake);

        String actualHangman = outputStream.toString().trim();
        assertEquals(expectedHangman, actualHangman);
    }

    @Test
    public void testPrintHangmanFourMistake() {
        int mistake = 4;
        String expectedHangman = """

                         ├─────────────┐
                         │             │
                         │
                         │
                         │
                         │
                         │
                         │
                         │
                         │
                         │
                     ‾‾‾‾‾‾‾‾‾
            """;

        visualizerHangman.print(mistake);

        String actualHangman = outputStream.toString();
        assertEquals(expectedHangman, actualHangman);
    }

    @Test
    public void testPrintHangmanSevenMistake() {
        int mistake = 7;
        String expectedHangman = """

                         ├─────────────┐
                         │             │
                         │            ───
                         │          │     │
                         │            ─┐─
                         │            ╱│
                         │           / │
                         │             │
                         │
                         │
                         │
                     ‾‾‾‾‾‾‾‾‾
            """;

        visualizerHangman.print(mistake);

        String actualHangman = outputStream.toString();
        assertEquals(expectedHangman, actualHangman);
    }

    @Test
    public void testPrintHangmanMaxMistake() {
        int mistake = 10;
        String expectedHangman = """

                         ├─────────────┐
                         │             │
                         │            ───
                         │          │ ✖ ✖│ черешня)
                         │            ─┐─
                         │            ╱│╲
                         │           / │ \\
                         │             │
                         │            / \\
                         │           /   \\
                         │
                     ‾‾‾‾‾‾‾‾‾
            """;

        visualizerHangman.print(mistake);

        String actualHangman = outputStream.toString();
        assertEquals(expectedHangman, actualHangman);
    }
}
