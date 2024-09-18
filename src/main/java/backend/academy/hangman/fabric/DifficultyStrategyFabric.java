package backend.academy.hangman.fabric;

import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.strategy.DifficultyStrategy;
import backend.academy.hangman.strategy.EasyDifficultyStrategy;
import backend.academy.hangman.strategy.HardDifficultyStrategy;
import backend.academy.hangman.strategy.MediumDifficultyStrategy;
import backend.academy.hangman.word.WordManager;
import lombok.RequiredArgsConstructor;

/**
 * Фабрика для создания стратегий сложности
 * @see DifficultyStrategy
 * @see EasyDifficultyStrategy
 * @see MediumDifficultyStrategy
 * @see HardDifficultyStrategy
 */
@RequiredArgsConstructor
public class DifficultyStrategyFabric {
    private final VisualizerHangman visualizerHangman;
    private final WordManager wordManager;

    public DifficultyStrategy createDifficultyStrategy(DifficultyEnum difficulty) {
        return switch (difficulty) {
            case EASY -> new EasyDifficultyStrategy(wordManager, visualizerHangman);
            case MEDIUM -> new MediumDifficultyStrategy(wordManager, visualizerHangman);
            case HARD -> new HardDifficultyStrategy(wordManager, visualizerHangman);
        };
    }
}
