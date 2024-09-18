package backend.academy.hangman.setting;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.fabric.DifficultyStrategyFabric;
import backend.academy.hangman.strategy.EasyDifficultyStrategy;
import backend.academy.hangman.word.Word;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Pattern;

@ExtendWith(MockitoExtension.class)
public class GameConfiguratorTest {

    @Mock
    private DifficultyStrategyFabric difficultyStrategyFabric;

    @Mock
    private EasyDifficultyStrategy easyStrategy;

    @InjectMocks
    private GameConfigurator gameConfigurator;

    @Test
    public void testSetUpGame() {
        DifficultyEnum difficulty = DifficultyEnum.EASY;
        Category category = Category.ANIMALS;
        Word word = new Word("ДОМ", "Постройка");
        Pattern pattern = Pattern.compile("[А-Яа-я]");
        int maxAttempts = 10;

        when(difficultyStrategyFabric.createDifficultyStrategy(difficulty)).thenReturn(easyStrategy);
        when(easyStrategy.getWord(category)).thenReturn(word);
        when(easyStrategy.getPattern()).thenReturn(pattern);
        when(easyStrategy.getMaxAttempts()).thenReturn(maxAttempts);

        Settings settings = gameConfigurator.setUpGame(difficulty, category);

        assertNotNull(settings);
        assertEquals(easyStrategy, settings.difficulty());
        assertEquals(category, settings.category());
        assertEquals(word, settings.word());
        assertEquals(word.word(), settings.comparator().word());
        assertEquals(pattern, settings.comparator().pattern());
        assertEquals(maxAttempts, settings.comparator().wrongLetters().size());
    }
}
