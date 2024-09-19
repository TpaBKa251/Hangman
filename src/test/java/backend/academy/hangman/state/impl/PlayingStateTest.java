package backend.academy.hangman.state.impl;

import backend.academy.hangman.Game;
import backend.academy.hangman.comparison.Comparator;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.setting.Settings;
import backend.academy.hangman.strategy.DifficultyStrategy;
import backend.academy.hangman.word.Word;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayingStateTest {

    private PrintStream originalSystemOut = System.out;

    @Mock
    private Reader reader;

    @Mock
    private Game game;

    @Mock
    private Settings settings;

    @Mock
    private Comparator comparator;

    @Mock
    private DifficultyStrategy difficultyStrategy;

    @Mock
    private Word word;

    @InjectMocks
    PlayingState playingState;

    @BeforeEach
    public void setUp() {
        when(game.settings()).thenReturn(settings);
        when(settings.comparator()).thenReturn(comparator);
        when(settings.difficulty()).thenReturn(difficultyStrategy);
        when(difficultyStrategy.getMaxAttempts()).thenReturn(3);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testHandleWin() {
        GameStates expectedState = GameStates.WIN;

        when(reader.read()).thenReturn("А");
        when(comparator.isValid("А")).thenReturn(true);
        when(comparator.compareWithUsedLetters('А')).thenReturn(false);
        when(comparator.compare('А')).thenReturn(true);

        playingState.handle(game);

        GameStates actualState = playingState.nextState();

        assertEquals(expectedState, actualState);
    }

    @Test
    public void testHandleLose_attemptsEnded() {
        GameStates expectedState = GameStates.LOSE;

        when(reader.read()).thenReturn("А", "Б", "В");
        when(comparator.isValid(anyString())).thenReturn(true);
        when(comparator.compareWithUsedLetters(anyChar())).thenReturn(false);
        when(comparator.compare(anyChar())).thenReturn(false);
        when(settings.word()).thenReturn(word);

        playingState.handle(game);

        GameStates actualState = playingState.nextState();

        assertEquals(expectedState, actualState);
    }

    @Test
    public void testHandleLose_gaveUpInStart() {
        GameStates expectedState = GameStates.LOSE;

        when(reader.read()).thenReturn("0");

        playingState.handle(game);

        GameStates actualState = playingState.nextState();

        assertEquals(expectedState, actualState);
    }

    @Test
    public void testHandleLose_gaveUpInMiddle() {
        List<Character> correctLetters = List.of('А', '_');
        GameStates expectedState = GameStates.LOSE;

        when(reader.read()).thenReturn("А", "0");
        when(comparator.isValid("А")).thenReturn(true);
        when(comparator.compareWithUsedLetters('А')).thenReturn(false);
        when(comparator.compare('А')).thenReturn(true);
        when(comparator.correctLetters()).thenReturn(correctLetters);

        playingState.handle(game);

        GameStates actualState = playingState.nextState();

        assertEquals(expectedState, actualState);
    }

    @Test
    public void testInvalidInput() {
        when(reader.read()).thenReturn("1", "А");
        when(comparator.isValid("А")).thenReturn(true);
        when(comparator.isValid("1")).thenReturn(false);
        when(comparator.compareWithUsedLetters('А')).thenReturn(false);
        when(comparator.compare('А')).thenReturn(true);

        playingState.handle(game);

        verify(comparator).compare('А');
        verify(comparator, never()).compare('1');
    }

    @Test
    public void testCompareWithUsedLetters() {
        when(reader.read()).thenReturn("А", "0");
        when(comparator.isValid("А")).thenReturn(true);
        when(comparator.compareWithUsedLetters('А')).thenReturn(true);

        playingState.handle(game);

        verify(comparator, never()).compare(anyChar());
    }
}


