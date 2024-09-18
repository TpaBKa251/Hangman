package backend.academy.hangman.setting;

import backend.academy.hangman.comparison.Comparator;
import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.fabric.DifficultyStrategyFabric;
import backend.academy.hangman.strategy.DifficultyStrategy;
import backend.academy.hangman.word.Word;
import lombok.RequiredArgsConstructor;

/**
 * Класс для вторичной настройки игры (выбор стратегии {@link DifficultyStrategy}
 * на основе сложности {@link DifficultyEnum}, выбор слова {@link Word} на основе категории {@link Category},
 * настройка компаратора {@link Comparator})
 * @see PlayerPreferences
 */
@RequiredArgsConstructor
public class GameConfigurator {
    private final DifficultyStrategyFabric difficultyStrategyFabric;

    /**
     * Метод вторичной настройки
     * @param difficulty сложность игры
     * @param category категория слова
     * @return объект {@link Settings}
     */
    public Settings setUpGame(DifficultyEnum difficulty, Category category) {
        DifficultyStrategy difficultyStrategy = difficultyStrategyFabric.createDifficultyStrategy(difficulty);

        Word word = difficultyStrategy.getWord(category);

        Comparator comparator = new Comparator(
            word.word(),
            difficultyStrategy.getPattern(),
            difficultyStrategy.getMaxAttempts());

        return new Settings(difficultyStrategy, category, word, comparator);
    }
}
