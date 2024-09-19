package backend.academy.hangman.state.impl;

import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.input.Reader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoseStateTest {

    @Mock
    private Reader reader;

    @InjectMocks
    private LoseState loseState;

    @Test
    public void testNextStateSettings() {
        GameStates expectedState = GameStates.SETTINGS;

        when(reader.read()).thenReturn("1");

        GameStates actualState = loseState.nextState();

        assertEquals(expectedState, actualState);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "2", ""})
    public void testNextStateExit(String input) {
        GameStates expectedState = GameStates.EXIT;

        when(reader.read()).thenReturn(input);

        GameStates actualState = loseState.nextState();

        assertEquals(expectedState, actualState);
    }
}
