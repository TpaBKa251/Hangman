package backend.academy.hangman.state.impl;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.output.Interface;
import backend.academy.hangman.state.GameBaseState;
import lombok.RequiredArgsConstructor;

/**
 * Класс состояния поражения (проигрыша).
 * Выводит сообщение о проигрыше и предлагает начать новую попытку или выйти из игры
 */
@RequiredArgsConstructor
public class LoseState implements GameBaseState {
    private final Reader reader;

    @Override
    public void handle(Game game) {
        Interface.print("""
                             uu$$$$$$$$$$$uu
                          uu$$$$$$$$$$$$$$$$$uu
                         u$$$$$$$$$$$$$$$$$$$$$u
                        u$$$$$$$$$$$$$$$$$$$$$$$u
                       u$$$$$$$$$$$$$$$$$$$$$$$$$u
                       u$$$$$$*   *$$$*   *$$$$$$u
                       *$$$$*      u$u       $$$$*
                        $$$u       u$u       u$$$
                        $$$u      u$$$u      u$$$
                         *$$$$uu$$$   $$$uu$$$$*
                          *$$$$$$$*   *$$$$$$$*
                            u$$$$$$$u$$$$$$$u
                             u$*$*$*$*$*$*$u
                  uuu        $$u$ $ $ $ $u$$       uuu
                  u$$$$       $$$$$u$u$u$$$       u$$$$
                  $$$$$uu      *$$$$$$$$$*     uu$$$$$$
                u$$$$$$$$$$$uu    *****    uuuu$$$$$$$$$
                $$$$***$$$$$$$$$$uuu   uu$$$$$$$$$***$$$*
                 ***      **$$$$$$$$$$$uu **$***
                          uuuu **$$$$$$$$$$uuu
                 u$$$uuu$$$$$$$$$uu **$$$$$$$$$$$uuu$$$
                 $$$$$$$$$$****           **$$$$$$$$$$$*
                   *$$$$$*                      **$$$$**
                     $$$*                         $$$$*
                        _      ____   _____ ______
                       | |    / __ \\ / ____|  ____|
                       | |   | |  | | (___ | |__
                       | |   | |  | |\\___ \\|  __|
                       | |___| |__| |____) | |____
                       |______\\____/|_____/|______|

                Загаданное слово:""" + game.settings().word().word() + """

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
