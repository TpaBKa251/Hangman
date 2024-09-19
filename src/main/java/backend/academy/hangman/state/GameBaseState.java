package backend.academy.hangman.state;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;

/**
 * Интерфейс для реализации паттерна состояния. Содержит два основных метода всех состояний
 */
public interface GameBaseState {
    /**
     * Метод с основной логикой состояний
     * @param game экземпляр класса игры {@link Game}
     */
    void handle(Game game);

    /**
     * Метод получения следующего состояния
     * @return элемент перечисления {@link GameStates}, означающий следующее состояние
     */
    GameStates nextState();
}
