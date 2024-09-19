package backend.academy.hangman.state.impl;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.output.Interface;
import backend.academy.hangman.state.GameBaseState;
import lombok.RequiredArgsConstructor;

/**
 * Класс состояния победы. Выводит победное сообщение и предлагает начать новую попытку или выйти из игры
 */
@RequiredArgsConstructor
public class WinState implements GameBaseState {
    private final Reader reader;

    @Override
    public void handle(Game game) {
        Interface.print("""
           ⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⢰⣦⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
           ⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⣼⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⣠⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀ ⣠⣾⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
            ⢠⣤⣤⣤⡀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣧⣤⣤⣤⣤⣤⣤⣤⣤⣄⡀⠀
            ⢸⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄
            ⢸⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇
            ⢸⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀
            ⢸⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀
            ⢸⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀
            ⢸⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀
            ⢸⣿⣿⣿⡇⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀⠀
            __          _______ _   _
            \\ \\        / /_   _| \\ | |
             \\ \\  /\\  / /  | | |  \\| |
              \\ \\/  \\/ /   | | | . ` |
               \\  /\\  /   _| |_| |\\  |
                \\/  \\/   |_____|_| \\_|

            Начать новую игру?
                1. Да
                2. Нет""", true);
    }

    @Override
    public GameStates nextState() {
        String ans = reader.read();

        if ("1".equals(ans)) {
            return GameStates.SETTINGS;
        }

        return GameStates.EXIT;
    }
}
