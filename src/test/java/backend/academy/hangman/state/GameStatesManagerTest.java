package backend.academy.hangman.state;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.fabric.GameStatesFabric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Тесты менеджера состояний GameStatesManager")
@ExtendWith(MockitoExtension.class)
public class GameStatesManagerTest {

    @Mock
    private GameStatesFabric gameStatesFabric;

    @Mock
    private GameBaseState settingsState;

    @Mock
    private GameBaseState playingState;

    @Mock
    private GameBaseState winState;

    @Mock
    private GameBaseState loseState;

    @Mock
    private Game game;

    private GameStatesManager gameStatesManager;

    @BeforeEach
    public void setUp() {
        when(gameStatesFabric.createGameState(GameStates.SETTINGS)).thenReturn(settingsState);
        when(gameStatesFabric.createGameState(GameStates.PLAYING)).thenReturn(playingState);
        when(gameStatesFabric.createGameState(GameStates.WIN)).thenReturn(winState);
        when(gameStatesFabric.createGameState(GameStates.LOSE)).thenReturn(loseState);

        gameStatesManager = new GameStatesManager(gameStatesFabric);
    }

    @DisplayName("Тест, проверяющий начальное состояние")
    @Test
    public void testInitialState() {
        GameBaseState expectedState = settingsState;

        GameBaseState actualState = gameStatesManager.currentState();

        assertEquals(expectedState, actualState);
    }

    @DisplayName("Тест, проверяющий метод переключения между состояниями")
    @Test
    public void testSwitchState() {
        GameBaseState expectedState1 = playingState;
        GameBaseState expectedState2 = winState;
        GameBaseState expectedState3 = loseState;
        GameBaseState actualState;

        gameStatesManager.switchState(GameStates.PLAYING);
        actualState = gameStatesManager.currentState();
        assertEquals(expectedState1, actualState);

        gameStatesManager.switchState(GameStates.WIN);
        actualState = gameStatesManager.currentState();
        assertEquals(expectedState2, actualState);

        gameStatesManager.switchState(GameStates.LOSE);
        actualState = gameStatesManager.currentState();
        assertEquals(expectedState3, actualState);
    }

    @DisplayName("Тест, проверяющий автоматическое переключение состояний")
    @Test
    public void testStateHandleAndNextState() {
        GameBaseState expectedState1 = playingState;
        GameBaseState expectedState2 = winState;
        GameBaseState actualState;

        when(settingsState.nextState()).thenReturn(GameStates.PLAYING);
        gameStatesManager.stateHandle(game);
        verify(settingsState).handle(game);
        verify(settingsState).nextState();
        actualState = gameStatesManager.currentState();
        assertEquals(expectedState1, actualState);

        when(playingState.nextState()).thenReturn(GameStates.WIN);
        gameStatesManager.stateHandle(game);
        verify(playingState).handle(game);
        verify(playingState).nextState();
        actualState = gameStatesManager.currentState();
        assertEquals(expectedState2, actualState);
    }
}
