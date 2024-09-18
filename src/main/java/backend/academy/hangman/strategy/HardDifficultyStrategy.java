package backend.academy.hangman.strategy;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.word.Word;
import backend.academy.hangman.word.WordManager;
import java.util.regex.Pattern;

/**
 * Класс стратегии высокой сложности. Наследуется от абстрактного класса {@link DifficultyStrategy}.
 * Имеет отличающиеся от базового класса константы {@code INPUT_PATTERN} и {@code ERROR_VALID_MESSAGE}
 */
public class HardDifficultyStrategy extends DifficultyStrategy {
    private static final int MAX_ATTEMPTS = 6;
    private static final Pattern INPUT_PATTERN = Pattern.compile("[А-Яа-я- ]");
    private static final String ERROR_VALID_MESSAGE = "Вводите по одной букве русского алфавита, дефис или пробел!";

    /**
     * Использует конструктор базового класса
     * @param wordManager экземпляр класса {@link WordManager}
     * @param visualizerHangman экземпляр класса {@link VisualizerHangman}
     */
    public HardDifficultyStrategy(WordManager wordManager, VisualizerHangman visualizerHangman) {
        super(wordManager, visualizerHangman);
    }

    /**
     * Метод получения случайного слова высокой сложности для заданной категории слова
     * @param category категория {@link Category} слова
     * @return объект {@link Word}
     */
    @Override
    public Word getWord(Category category) {
        return wordManager.getWord(DifficultyEnum.HARD, category);
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public DifficultyEnum getDifficulty() {
        return DifficultyEnum.HARD;
    }

    @Override
    public Pattern getPattern() {
        return INPUT_PATTERN;
    }

    @Override
    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }
}
