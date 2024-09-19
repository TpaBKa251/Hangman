package backend.academy.hangman.state;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;

public interface GameBaseState {
    void handle(Game game);

    GameStates nextState();
}
