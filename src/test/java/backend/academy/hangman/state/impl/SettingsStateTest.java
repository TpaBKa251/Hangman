package backend.academy.hangman.state.impl;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.setting.GameConfigurator;
import backend.academy.hangman.setting.PlayerPreferences;
import backend.academy.hangman.setting.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SettingsStateTest {

    @Mock
    PlayerPreferences playerPreferences;

    @Mock
    GameConfigurator gameConfigurator;

    @Mock
    Game game;

    @Mock
    Settings settings;

    @InjectMocks
    SettingsState settingsState;

    @Test
    public void testHandle() {
        DifficultyEnum difficulty = DifficultyEnum.EASY;
        Category category = Category.ANIMALS;

        when(playerPreferences.selectDifficulty()).thenReturn(difficulty);
        when(playerPreferences.selectCategory()).thenReturn(category);
        when(gameConfigurator.setUpGame(difficulty, category)).thenReturn(settings);

        settingsState.handle(game);

        verify(playerPreferences).selectDifficulty();
        verify(playerPreferences).selectCategory();
        verify(gameConfigurator).setUpGame(difficulty, category);
        verify(game).settings(settings);
    }

    @Test
    public void testNextState() {
        GameStates expectedState = GameStates.PLAYING;

        GameStates actualState = settingsState.nextState();

        assertEquals(expectedState, actualState);
    }
}
