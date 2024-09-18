package backend.academy.hangman.input;

import java.util.Scanner;
import lombok.RequiredArgsConstructor;

/**
 * Класс для чтения пользовательского ввода
 */
@RequiredArgsConstructor
public class Reader {
    private final Scanner scanner;

    public String read() {
        return scanner.nextLine();
    }
}
