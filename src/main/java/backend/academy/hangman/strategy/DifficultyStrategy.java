package backend.academy.hangman.strategy;


import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.word.Word;
import backend.academy.hangman.word.WordManager;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;

/**
 * Абстрактный класс для реализации паттерна стратегия.
 * Имеет общий конструктор, общие методы, а также абстрактные методы
 */
@RequiredArgsConstructor
public abstract class DifficultyStrategy {
    /**
     * Базовое количество попыток
     */
    static final int MAX_ATTEMPTS = 10;
    /**
     * Базовый паттерн для сравнения с пользовательским вводом
     */
    private static final Pattern INPUT_PATTERN = Pattern.compile("[А-Яа-я]");
    /**
     * Базовое сообщение об ошибке валидации пользовательского ввода
     */
    private static final String ERROR_VALID_MESSAGE = "Вводите по одной букве русского алфавита!";

    final WordManager wordManager;
    final VisualizerHangman visualizerHangman;

    /**
     * Метод для получения случайного слова
     * @param category категория {@link Category} слова
     * @return объект {@link Word}
     */
    public abstract Word getWord(Category category);

    public abstract int getMaxAttempts();

    public abstract DifficultyEnum getDifficulty();

    /**
     * Метод, выводящий виселицу в зависимости от текущего количества ошибок пользователя
     * @param mistake количество ошибок типа {@code int}
     */
    public void printGallows(int mistake) {
        visualizerHangman.print(mistake + (MAX_ATTEMPTS - getMaxAttempts()));
    }

    public Pattern getPattern() {
        return INPUT_PATTERN;
    }

    public String getErrorValidMessage() {
        return ERROR_VALID_MESSAGE;
    }
}
