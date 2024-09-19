package backend.academy.hangman.state.impl;

import backend.academy.hangman.Game;
import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.enums.GameStates;
import backend.academy.hangman.setting.GameConfigurator;
import backend.academy.hangman.setting.PlayerPreferences;
import backend.academy.hangman.state.GameBaseState;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SettingsState implements GameBaseState {
    private final PlayerPreferences playerPreferences;
    private final GameConfigurator gameConfigurator;

    @Override
    public void handle(Game game) {
        DifficultyEnum difficulty = playerPreferences.selectDifficulty();
        Category category = playerPreferences.selectCategory();

        game.settings(gameConfigurator.setUpGame(difficulty, category));
    }

    @Override
    public GameStates nextState() {
        return GameStates.PLAYING;
    }
}
