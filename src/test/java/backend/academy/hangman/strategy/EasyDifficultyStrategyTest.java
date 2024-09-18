package backend.academy.hangman.strategy;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.word.WordManager;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EasyDifficultyStrategyTest {

    @Mock
    private WordManager wordManager;

    @Mock
    private VisualizerHangman visualizerHangman;

    @InjectMocks
    private EasyDifficultyStrategy strategy;

    @Test
    public void testGetWord() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.EASY;
        Category expectedCategory = Category.ANIMALS;

        strategy.getWord(Category.ANIMALS);

        verify(wordManager).getWord(expectedDifficulty, expectedCategory);
    }

    @Test
    public void testGetMaxAttempts() {
        int expectedMaxAttempts = 10;

        int actualMaxAttempts = strategy.getMaxAttempts();

        assertEquals(expectedMaxAttempts, actualMaxAttempts);
    }

    @Test
    public void testGetDifficulty() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.EASY;

        DifficultyEnum actualDifficultyEnum = strategy.getDifficulty();

        assertEquals(expectedDifficulty, actualDifficultyEnum);
    }

    @Test
    public void testPrintGallows() {
        int currAttempts = 2;
        int maxAttempts = strategy.getMaxAttempts();
        int expectedAttempts = currAttempts + (DifficultyStrategy.MAX_ATTEMPTS - maxAttempts);

        strategy.printGallows(currAttempts);

        verify(visualizerHangman).print(expectedAttempts);
    }

    @Test
    public void testGetPattern() {
        String expectedPattern = Pattern.compile("[А-Яа-я]").pattern();

        String actualPattern = strategy.getPattern().pattern();

        assertEquals(expectedPattern, actualPattern);
    }

    @Test
    public void testGetErrorValidMessage() {
        String expectedMessage = "Вводите по одной букве русского алфавита!";

        String actualMessage = strategy.getErrorValidMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
