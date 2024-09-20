package backend.academy.hangman.fabric;

import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.strategy.DifficultyStrategy;
import backend.academy.hangman.strategy.EasyDifficultyStrategy;
import backend.academy.hangman.strategy.HardDifficultyStrategy;
import backend.academy.hangman.strategy.MediumDifficultyStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DisplayName("Тесты фабрики стратегий сложности DifficultyStrategyFabric")
@ExtendWith(MockitoExtension.class)
public class DifficultyStrategyFabricTest {

    @InjectMocks
    private DifficultyStrategyFabric difficultyStrategyFabric;

    @DisplayName("Тест создания стратегии легкой сложности")
    @Test
    public void testCreateEasyDifficultyStrategy() {
        DifficultyEnum difficulty = DifficultyEnum.EASY;

        DifficultyStrategy actualStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        assertInstanceOf(EasyDifficultyStrategy.class, actualStrategy);
    }

    @DisplayName("Тест создания стратегии средней сложности")
    @Test
    public void testCreateMediumDifficultyStrategy() {
        DifficultyEnum difficulty = DifficultyEnum.MEDIUM;

        DifficultyStrategy actualStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        assertInstanceOf(MediumDifficultyStrategy.class, actualStrategy);
    }

    @DisplayName("Тест создания стратегии высокой сложности")
    @Test
    public void testCreateHardDifficultyStrategy() {
        DifficultyEnum difficulty = DifficultyEnum.HARD;

        DifficultyStrategy actualStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        assertInstanceOf(HardDifficultyStrategy.class, actualStrategy);
    }
}
