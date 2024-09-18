package backend.academy.hangman.setting;

import backend.academy.hangman.comparison.Comparator;
import backend.academy.hangman.enums.Category;
import backend.academy.hangman.strategy.DifficultyStrategy;
import backend.academy.hangman.word.Word;

/**
 * Record, хранящий настройки игры
 * @param difficulty стратегия сложности {@link DifficultyStrategy}
 * @param category категория слова {@link Category}
 * @param word загаданное слово {@link Word}
 * @param comparator компаратор {@link Comparator}
 */
public record Settings(DifficultyStrategy difficulty, Category category, Word word, Comparator comparator) {
}
