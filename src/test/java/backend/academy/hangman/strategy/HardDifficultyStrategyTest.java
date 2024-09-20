package backend.academy.hangman.strategy;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.word.WordManager;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@DisplayName("Тесты класса HardDifficultyStrategy стратегии высокой сложности")
@ExtendWith(MockitoExtension.class)
public class HardDifficultyStrategyTest {

    @Mock
    private WordManager wordManager;

    @Mock
    private VisualizerHangman visualizerHangman;

    @InjectMocks
    private HardDifficultyStrategy strategy;

    @DisplayName("Тест метода получения слова")
    @Test
    public void testGetWord() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.HARD;
        Category expectedCategory = Category.ANIMALS;

        strategy.getWord(Category.ANIMALS);

        verify(wordManager).getWord(expectedDifficulty, expectedCategory);
    }

    @DisplayName("Тест метода получения максимального количества попыток")
    @Test
    public void testGetMaxAttempts() {
        int expectedMaxAttempts = 6;

        int actualMaxAttempts = strategy.getMaxAttempts();

        assertEquals(expectedMaxAttempts, actualMaxAttempts);
    }

    @DisplayName("Тест метода получения текущей сложности игры")
    @Test
    public void testGetDifficulty() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.HARD;

        DifficultyEnum actualDifficultyEnum = strategy.getDifficulty();

        assertEquals(expectedDifficulty, actualDifficultyEnum);
    }

    @DisplayName("Тест, проверяющий корректность вызова метода вывода текущей стадии виселицы")
    @Test
    public void testPrintGallows() {
        int currAttempts = 2;
        int maxAttempts = strategy.getMaxAttempts();
        int expectedAttempts = currAttempts + (DifficultyStrategy.MAX_ATTEMPTS - maxAttempts);

        strategy.printGallows(currAttempts);

        verify(visualizerHangman).print(expectedAttempts);
    }

    @DisplayName("Тест метода получения текущего паттерна для валидации")
    @Test
    public void testGetPattern() {
        String expectedPattern = Pattern.compile("[А-Яа-я- ]").pattern();

        String actualPattern = strategy.getPattern().pattern();

        assertEquals(expectedPattern, actualPattern);
    }

    @DisplayName("Тест метода получения текущего сообщения об ошибке валидации")
    @Test
    public void testGetErrorValidMessage() {
        String expectedMessage = "Вводите по одной букве русского алфавита, дефис или пробел!";

        String actualMessage = strategy.getErrorValidMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
