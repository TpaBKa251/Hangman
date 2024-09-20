package backend.academy.hangman.comparison;

import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты класса Comparator для сравнения строк и символов")
public class ComparatorTest {

    private Comparator comparator;

    @BeforeEach
    public void setUp() {
        String word = "ДОМ";
        Pattern pattern = Pattern.compile("[А-Яа-я]");
        int maxAttempts = 8;

        comparator = new Comparator(word, pattern, maxAttempts);
    }

    @DisplayName("Тест конструктора и корректности заполнения списков")
    @Test
    public void testConstructor() {
        String expectedWord = "ДОМ";

        String actualWord = comparator.word();

        assertEquals(expectedWord, actualWord);
        assertThat(comparator.correctLetters()).containsExactly('_', '_', '_');
        assertThat(comparator.wrongLetters()).containsExactly('_', '_', '_', '_', '_', '_', '_', '_');
    }

    @DisplayName("Тест валидации строки при валидных строках")
    @ParameterizedTest
    @ValueSource(strings = {"Л", "ф"})
    public void testValidateAvailableLetters(String line) {
        boolean valid = comparator.isValid(line);

        assertTrue(valid);
    }

    @DisplayName("Тест валидации строки при невалидных строках")
    @ParameterizedTest
    @ValueSource(strings = {"A", "q", "4", ".", "%", "по", "\n" , ""})
    public void testValidateNonAvailableLetters(String line) {
        boolean valid = comparator.isValid(line);

        assertFalse(valid);
    }

    @DisplayName("Тест сравнения с правильным символом")
    @Test
    public void testCompareWithCorrectLetters() {
        boolean correct = comparator.compare('Д');

        assertTrue(correct);
        assertThat(comparator.correctLetters()).containsExactly('Д', '_', '_');
    }

    @DisplayName("Тест сравнения с неправильным символом")
    @Test
    public void testCompareWithWrongLetters() {
        boolean correct = comparator.compare('Л');

        assertFalse(correct);
        assertThat(comparator.wrongLetters()).containsExactly('Л', '_', '_', '_', '_', '_', '_', '_');
    }

    @DisplayName("Тест сравнения с использованными правильными символами")
    @Test
    public void testCompareWithUsedCorrectLetters() {
        comparator.compare('Д');

        boolean used = comparator.compareWithUsedLetters('Д');

        assertTrue(used);
    }

    @DisplayName("Тест сравнения с использованными неправильными символами")
    @Test
    public void testCompareWithUsedWrongLetters() {
        comparator.compare('Л');

        boolean used = comparator.compareWithUsedLetters('Л');

        assertTrue(used);
    }
}
