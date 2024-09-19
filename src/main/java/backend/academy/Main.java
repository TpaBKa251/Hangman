package backend.academy;

import backend.academy.hangman.Game;
import backend.academy.hangman.fabric.DifficultyStrategyFabric;
import backend.academy.hangman.fabric.GameStatesFabric;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.output.VisualizerHangman;
import backend.academy.hangman.setting.GameConfigurator;
import backend.academy.hangman.setting.PlayerPreferences;
import backend.academy.hangman.state.GameStatesManager;
import backend.academy.hangman.word.WordManager;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        WordManager wordManager = new WordManager(new SecureRandom());

        VisualizerHangman visualizerHangman = new VisualizerHangman();

        DifficultyStrategyFabric difficultyStrategyFabric = new DifficultyStrategyFabric(
            visualizerHangman,
            wordManager);

        GameStatesManager gameStatesManager = getGameStatesManager(difficultyStrategyFabric);

        Game game = new Game(gameStatesManager);

        game.play();
    }

    private static GameStatesManager getGameStatesManager(DifficultyStrategyFabric difficultyStrategyFabric) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        Reader reader = new Reader(scanner);

        GameConfigurator gameConfigurator = new GameConfigurator(difficultyStrategyFabric);

        PlayerPreferences playerPreferences = new PlayerPreferences(reader, new SecureRandom());

        GameStatesFabric gameStatesFabric = new GameStatesFabric(reader, playerPreferences, gameConfigurator);

        return new GameStatesManager(gameStatesFabric);
    }
}
