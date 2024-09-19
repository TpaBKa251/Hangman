package backend.academy.hangman.state;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.fabric.GameStatesFabric;
import java.util.EnumMap;
import java.util.Map;
import lombok.Getter;

public class GameStatesManager {
    private final Map<GameStates, GameBaseState> gameStates = new EnumMap<>(GameStates.class);
    @Getter
    private GameBaseState currentState;

    public GameStatesManager(GameStatesFabric gameStatesFabric) {
        gameStates.put(GameStates.SETTINGS, gameStatesFabric.createGameState(GameStates.SETTINGS));
        gameStates.put(GameStates.PLAYING, gameStatesFabric.createGameState(GameStates.PLAYING));
        gameStates.put(GameStates.LOSE, gameStatesFabric.createGameState(GameStates.LOSE));
        gameStates.put(GameStates.WIN, gameStatesFabric.createGameState(GameStates.WIN));

        currentState = gameStates.get(GameStates.SETTINGS);
    }

    public void switchState(GameStates newState) {
        currentState = gameStates.get(newState);
    }

    public void stateHandle(Game game) {
        currentState.handle(game);
        GameStates nextState = currentState.nextState();
        switchState(nextState);
    }
}
