package backend.academy.hangman.output;

import lombok.experimental.UtilityClass;

/**
 * Класс, выводящий текст в консоль
 */
@UtilityClass
@SuppressWarnings(value = "all")
public class Interface {
    /**
     * Статический метод, выводящий текст в консоль.
     * Флаг {@code newLine} определяет с новой строки выводить текст или продолжить на текущей
     * @param message текст для вывода
     * @param newLine флаг вывода с новой строки
     */
    public static void print(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }
}
