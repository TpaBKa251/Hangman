package backend.academy.hangman.state;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.fabric.GameStatesFabric;
import backend.academy.hangman.state.impl.LoseState;
import backend.academy.hangman.state.impl.PlayingState;
import backend.academy.hangman.state.impl.SettingsState;
import backend.academy.hangman.state.impl.WinState;
import java.util.EnumMap;
import java.util.Map;
import lombok.Getter;

/**
 * Класс для управления состояниями.
 * Занимается их созданием через фабрику {@link GameStatesFabric}, переключением и запуском в них методов
 * @see SettingsState
 * @see PlayingState
 * @see WinState
 * @see LoseState
 */
public class GameStatesManager {
    private final Map<GameStates, GameBaseState> gameStates = new EnumMap<>(GameStates.class);
    @Getter
    private GameBaseState currentState;

    /**
     * В конструкторе создаются все состояния,
     * в качестве начального выставляется состояние настройки {@link SettingsState}
     * @param gameStatesFabric фабрика состояний {@link GameStatesFabric}
     */
    public GameStatesManager(GameStatesFabric gameStatesFabric) {
        gameStates.put(GameStates.SETTINGS, gameStatesFabric.createGameState(GameStates.SETTINGS));
        gameStates.put(GameStates.PLAYING, gameStatesFabric.createGameState(GameStates.PLAYING));
        gameStates.put(GameStates.LOSE, gameStatesFabric.createGameState(GameStates.LOSE));
        gameStates.put(GameStates.WIN, gameStatesFabric.createGameState(GameStates.WIN));

        currentState = gameStates.get(GameStates.SETTINGS);
    }

    /**
     * Метод переключения состояния
     * @param newState элемент перечисления {@link GameStates}, означающий состояние, в которое нужно переключиться
     */
    public void switchState(GameStates newState) {
        currentState = gameStates.get(newState);
    }

    /**
     * Метод запуска основного метода состояний {@link GameBaseState#handle(Game)}.
     * Также получает следующее состояние из метода {@link GameBaseState#nextState()} и переключает на него
     * @param game экземпляр класса игры {@link Game}
     */
    public void stateHandle(Game game) {
        currentState.handle(game);
        GameStates nextState = currentState.nextState();
        switchState(nextState);
    }
}
