package backend.academy.hangman.strategy;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.word.Word;
import backend.academy.hangman.word.WordManager;

/**
 * Класс стратегии средней сложности. Наследуется от абстрактного класса {@link DifficultyStrategy}
 */
public class MediumDifficultyStrategy extends DifficultyStrategy {
    private static final int MAX_ATTEMPTS = 8;

    /**
     * Использует конструктор базового класса
     * @param wordManager экземпляр класса {@link WordManager}
     * @param visualizerHangman экземпляр класса {@link VisualizerHangman}
     */
    public MediumDifficultyStrategy(WordManager wordManager, VisualizerHangman visualizerHangman) {
        super(wordManager, visualizerHangman);
    }

    /**
     * Метод получения случайного слова средней сложности для заданной категории слова
     * @param category категория {@link Category} слова
     * @return объект {@link Word}
     */
    @Override
    public Word getWord(Category category) {
        return wordManager.getWord(DifficultyEnum.MEDIUM, category);
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public DifficultyEnum getDifficulty() {
        return DifficultyEnum.MEDIUM;
    }
}
