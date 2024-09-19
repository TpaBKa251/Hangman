package backend.academy.hangman.fabric;

import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.setting.GameConfigurator;
import backend.academy.hangman.setting.PlayerPreferences;
import backend.academy.hangman.state.GameBaseState;
import backend.academy.hangman.state.impl.LoseState;
import backend.academy.hangman.state.impl.PlayingState;
import backend.academy.hangman.state.impl.SettingsState;
import backend.academy.hangman.state.impl.WinState;
import lombok.RequiredArgsConstructor;

/**
 * Класс фабрики состояний
 * @see SettingsState
 * @see PlayingState
 * @see WinState
 * @see LoseState
 */
@RequiredArgsConstructor
public class GameStatesFabric {
    private final Reader reader;
    private final PlayerPreferences playerPreferences;
    private final GameConfigurator gameConfigurator;

    public GameBaseState createGameState(GameStates gameState) {
        return switch (gameState) {
            case GameStates.SETTINGS -> new SettingsState(playerPreferences, gameConfigurator);
            case PLAYING -> new PlayingState(reader);
            case WIN -> new WinState(reader);
            case LOSE -> new LoseState(reader);
            default -> null;
        };
    }
}
