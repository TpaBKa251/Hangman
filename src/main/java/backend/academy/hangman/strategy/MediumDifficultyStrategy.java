package backend.academy.hangman.strategy;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.word.Word;
import backend.academy.hangman.word.WordManager;

public class MediumDifficultyStrategy extends DifficultyStrategy {
    private static final int MAX_ATTEMPTS = 8;

    public MediumDifficultyStrategy(WordManager wordManager, VisualizerHangman visualizerHangman) {
        super(wordManager, visualizerHangman);
    }

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

/*
feat: добавлен класс MediumDifficultyStrategy

Добавлен класс MediumDifficultyStrategy для реализации средней сложности игры.

Определен метод getWord() для получения слова средней сложности.

Определен метод getMaxAttempts(), который возвращает максимальное число попыток (8).

Определен метод getDifficulty(), который возвращает текущий уровень сложности (MEDIUM).

Наследуется от класса DifficultyStrategy.
 */
