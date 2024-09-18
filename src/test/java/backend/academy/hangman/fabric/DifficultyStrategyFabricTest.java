package backend.academy.hangman.fabric;

import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.strategy.DifficultyStrategy;
import backend.academy.hangman.strategy.EasyDifficultyStrategy;
import backend.academy.hangman.strategy.HardDifficultyStrategy;
import backend.academy.hangman.strategy.MediumDifficultyStrategy;
import backend.academy.hangman.word.WordManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockitoExtension.class)
public class DifficultyStrategyFabricTest {

    @Mock
    private VisualizerHangman visualizerHangman;

    @Mock
    private WordManager wordManager;

    @InjectMocks
    private DifficultyStrategyFabric difficultyStrategyFabric;

    @Test
    public void testCreateEasyDifficultyStrategy() {
        DifficultyEnum difficulty = DifficultyEnum.EASY;

        DifficultyStrategy actualStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        assertInstanceOf(EasyDifficultyStrategy.class, actualStrategy);
    }

    @Test
    public void testCreateMediumDifficultyStrategy() {
        DifficultyEnum difficulty = DifficultyEnum.MEDIUM;

        DifficultyStrategy actualStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        assertInstanceOf(MediumDifficultyStrategy.class, actualStrategy);
    }

    @Test
    public void testCreateHardDifficultyStrategy() {
        DifficultyEnum difficulty = DifficultyEnum.HARD;

        DifficultyStrategy actualStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        assertInstanceOf(HardDifficultyStrategy.class, actualStrategy);
    }
}
