package backend.academy.hangman.comparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import lombok.Getter;

/**
 * Класс-компаратор для сравнения введенных символов пользователем с теми,
 * которые находятся в загаданном слове или уже были использованы, а также с разрешенными для ввода.
 * Хранит правильные и неправильные символы.
 * Для сравнения с символами разрешенными для ввода используется {@link Pattern}, который передается через конструктор
 */
@Getter
public class Comparator {
    private final String word;
    private final List<Character> correctLetters;
    private final List<Character> wrongLetters;
    private final Pattern pattern;
    private int countWrongLetters = 0;

    public Comparator(String word, Pattern pattern, int maxAttempts) {
        this.word = word;
        this.pattern = pattern;
        correctLetters = new ArrayList<>(Collections.nCopies(word.length(), '_'));
        wrongLetters = new ArrayList<>(Collections.nCopies(maxAttempts, '_'));
    }

    /**
     * Метод для валидации ввода (сравнение с разрешенными символами)
     * @param line ввод пользователя
     * @return {@code boolean} значение, означающее валидность строки
     */
    public boolean isValid(String line) {
        return line.length() == 1 && pattern.matcher(line).matches();
    }

    /**
     * Метод для сравнения введенного символа с теми, которые находятся в загаданном слове
     * @see Comparator#updateOutputWord(char)
     * @see Comparator#updateWrongLetters(char)
     * @param letter символ для сравнения
     * @return {@code boolean} значение, означающее находится символ в строке или нет
     */
    public boolean compare(char letter) {
        if (word.contains(letter + "")) {
            updateOutputWord(letter);

            return true;
        }

        updateWrongLetters(letter);

        return false;
    }

    /**
     * Метод для сравнения введенного символа с уже использованными (введенными)
     * @see Comparator#compareWithCorrectLetters(char)
     * @see Comparator#compareWithWrongLetters(char)
     * @param letter символ для сравнения
     * @return {@code boolean} значение, означающее использовался уже символ или нет
     */
    public boolean compareWithUsedLetters(char letter) {
        return compareWithCorrectLetters(letter) || compareWithWrongLetters(letter);
    }

    /**
     * Метод для сравнения с уже использованными правильными символами
     * @param letter символ для сравнения
     * @return {@code boolean} значение, означающее использовался уже символ или нет
     */
    private boolean compareWithCorrectLetters(char letter) {
        return correctLetters.contains(letter);
    }

    /**
     * Метод для сравнения с уже использованными неправильными символами
     * @param letter символ для сравнения
     * @return {@code boolean} значение, означающее использовался уже символ или нет
     */
    private boolean compareWithWrongLetters(char letter) {
        return wrongLetters.contains(letter);
    }


    /**
     * Метод обновляющий слово, которое выводится пользователю в процессе игры
     * @param letter символ для обновления
     */
    private void updateOutputWord(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                correctLetters.set(i, letter);
            }
        }
    }

    /**
     * Метод добавляющий неверные символы
     * @param letter символ для обновления
     */
    private void updateWrongLetters(char letter) {
        wrongLetters.set(countWrongLetters, letter);
        countWrongLetters++;
    }
}
