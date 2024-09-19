package backend.academy.hangman.fabric;

import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.state.GameBaseState;
import backend.academy.hangman.state.impl.LoseState;
import backend.academy.hangman.state.impl.PlayingState;
import backend.academy.hangman.state.impl.SettingsState;
import backend.academy.hangman.state.impl.WinState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class GameStatesFabricTest {

    @InjectMocks
    private GameStatesFabric gameStatesFabric;

    @Test
    public void testCreateSettingsState() {
        Class<SettingsState> expectedClass = SettingsState.class;

        GameBaseState actualState = gameStatesFabric.createGameState(GameStates.SETTINGS);

        assertInstanceOf(expectedClass, actualState);
    }

    @Test
    public void testCreatePlayingState() {
        Class<PlayingState> expectedClass = PlayingState.class;

        GameBaseState actualState = gameStatesFabric.createGameState(GameStates.PLAYING);

        assertInstanceOf(expectedClass, actualState);
    }

    @Test
    public void testCreateWinState() {
        Class<WinState> expectedClass = WinState.class;

        GameBaseState actualState = gameStatesFabric.createGameState(GameStates.WIN);

        assertInstanceOf(expectedClass, actualState);
    }

    @Test
    public void testCreateLoseState() {
        Class<LoseState> expectedClass = LoseState.class;

        GameBaseState actualState = gameStatesFabric.createGameState(GameStates.LOSE);

        assertInstanceOf(expectedClass, actualState);
    }

    @Test
    public void testCreateNullState() {
        GameBaseState actualState = gameStatesFabric.createGameState(GameStates.EXIT);

        assertNull(actualState);
    }
}
