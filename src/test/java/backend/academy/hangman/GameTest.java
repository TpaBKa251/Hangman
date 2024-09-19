package backend.academy.hangman;

import backend.academy.hangman.setting.Settings;
import backend.academy.hangman.state.GameBaseState;
import backend.academy.hangman.state.GameStatesManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Mock
    private GameStatesManager gameStatesManager;

    @Mock
    private Settings settings;

    @Mock
    GameBaseState gameBaseState;

    @InjectMocks
    private Game game;

    @BeforeEach
    public void setUp() {
        game.settings(settings);
    }

    @Test
    public void testPlayGame() {
        when(gameStatesManager.currentState()).thenReturn(gameBaseState, (GameBaseState) null);

        game.play();

        verify(gameStatesManager, times(2)).currentState();
        verify(gameStatesManager, times(1)).stateHandle(game);
    }

    @Test
    public void testPlayGameWithMultipleStates() {
        when(gameStatesManager.currentState()).thenReturn(gameBaseState, gameBaseState, null);

        game.play();

        verify(gameStatesManager, times(3)).currentState();
        verify(gameStatesManager, times(2)).stateHandle(game);
    }


    @Test
    public void testPlayLoop() {
        when(gameStatesManager.currentState())
            .thenReturn(gameBaseState)
            .thenReturn(gameBaseState)
            .thenReturn(null);

        game.play();

        InOrder inOrder = inOrder(gameStatesManager);
        inOrder.verify(gameStatesManager, times(1)).currentState();
        inOrder.verify(gameStatesManager, times(1)).stateHandle(game);
        inOrder.verify(gameStatesManager, times(1)).currentState();
        inOrder.verify(gameStatesManager, times(1)).stateHandle(game);
        inOrder.verify(gameStatesManager, times(1)).currentState();
    }
}

