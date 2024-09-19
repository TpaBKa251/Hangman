package backend.academy.hangman.state.impl;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.output.Interface;
import backend.academy.hangman.state.GameBaseState;
import lombok.RequiredArgsConstructor;

/**
 * Класс состояния процесса игры. В нем находится основная логика игры
 * @see backend.academy.hangman.comparison.Comparator
 * @see backend.academy.hangman.setting.Settings
 * @see Game
 * @see Reader
 */
@RequiredArgsConstructor
public class PlayingState implements GameBaseState {
    private int currAttempt;
    private boolean win;
    private final Reader reader;

    /**
     * Метод с логикой игры
     * @param game экземпляр класса игры {@link Game}
     */
    @Override
    public void handle(Game game) {
        startGame(game);

        while (game.settings().difficulty().getMaxAttempts() > currAttempt) {

            String line = reader.read().toUpperCase();

            if ("0".equals(line)) {
                break;
            } else {
                if (game.settings().comparator().isValid(line)) {
                    char symbol = line.charAt(0);
                    compare(game, symbol);
                } else {
                    Interface.print(game.settings().difficulty().getErrorValidMessage(), true);
                }
            }

            if (win) {
                break;
            }
        }
    }

    /**
     * Метод получения следующего состояния в зависимости от исхода игры
     * @return элемент перечисления {@link GameStates}
     */
    @Override
    public GameStates nextState() {
        if (win) {
            return GameStates.WIN;
        }

        return GameStates.LOSE;
    }

    /**
     * Метод для сброса таких данных об игре, как текущая попытка и флаг о том, что игрок победил
     * @param game экземпляр класса игры
     */
    private void startGame(Game game) {
        currAttempt = 0;
        win = false;

        Interface.print("Игра началась!", true);
        printInterface(game, false);
    }

    /**
     * Метод для сравнения введенного символа с уже введенными и с теми, которые находятся в загаданном слове
     * @param game экземпляр класса игры
     * @param symbol символ для сравнения
     */
    private void compare(Game game, char symbol) {
        if (game.settings().comparator().compareWithUsedLetters(symbol)) {
            Interface.print("Вы уже вводили этот символ", true);
        } else {
            if (!game.settings().comparator().compare(symbol)) {
                wrongGuess(game);
            } else {
                correctGuess(game);
            }
        }
    }

    /**
     * Метод для вывода сообщения о вводе неправильного символа
     * @param game экземпляр класса игры
     */
    private void wrongGuess(Game game) {
        Interface.print("""
             __      _____  ___  _  _  ___   _____
             \\ \\    / / _ \\/ _ \\| \\| |/ __| /     \\
              \\ \\/\\/ /|   / (_) | .` | (_ || () () |
               \\_/\\_/ |_|_\\\\___/|_|\\_|\\___| \\  ^  /
                                             |||||\
            """, true);

        currAttempt++;

        printInterface(game, false);

        if (currAttempt >= game.settings().difficulty().getMaxAttempts() / 2
            && currAttempt < game.settings().difficulty().getMaxAttempts()) {
            Interface.print("\nПодсказка: " + game.settings().word().help(), true);
        }
    }

    /**
     * Метод для вывода сообщения о вводе правильного символа
     * @param game экземпляр класса игры
     */
    private void correctGuess(Game game) {
        Interface.print("""
               ___ ___  ___ ___ ___ ___ _____\s
              / __/ _ \\| _ \\ _ \\ __/ __|_   _|
             | (_| (_) |   /   / _| (__  | | \s
              \\___\\___/|_|_\\_|_\\___\\___| |_| \s
                                              \
            """, true);

        printInterface(game, true);
    }

    /**
     * Метод вывода интерфейса игры.
     * Сюда входят: виселица, сложность, категория, оставшиеся попытки, угадываемое слово и неверные символы
     * @param game экземпляр класса игры
     * @param isCorrect флаг, означающий был ввод пользователя верным или нет
     */
    private void printInterface(Game game, boolean isCorrect) {
        game.settings().difficulty().printGallows(currAttempt);

        Interface.print("\nСложность: " + game.settings().difficulty().getDifficulty()
            + "\nКатегория: " + game.settings().category()
            + "\nОставшееся количество попыток: " + (game.settings().difficulty().getMaxAttempts() - currAttempt
            + "\n\nСлово:"), true);

        if (isCorrect) {
            win = true;

            for (char letter : game.settings().comparator().correctLetters()) {
                Interface.print(letter + "", false);

                if (letter == '_') {
                    win = false;
                }
            }
        } else {
            for (char letter : game.settings().comparator().correctLetters()) {
                Interface.print(letter + "", false);
            }
        }

        Interface.print("\nНеправильные символы: ", true);

        for (char letter : game.settings().comparator().wrongLetters()) {
            Interface.print(letter + "", false);
        }

        Interface.print("", true);
    }
}
