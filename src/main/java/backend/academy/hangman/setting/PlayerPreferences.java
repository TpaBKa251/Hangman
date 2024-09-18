package backend.academy.hangman.setting;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.output.Interface;
import backend.academy.hangman.output.PlayerPreferencesMessages;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerPreferences {
    private final Reader reader;
    private final SecureRandom rand;
    private final PlayerPreferencesMessages messages = PlayerPreferencesMessages.initialize();

    public DifficultyEnum selectDifficulty() {
        DifficultyEnum[] difficulties = DifficultyEnum.values();

        Interface.print(messages.selectDifficultyMessage(), true);

        String difficulty = reader.read();

        try {
            int ans = Integer.parseInt(difficulty);

            return difficulties[ans - 1];
        } catch (Exception e) {
            return difficulties[rand.nextInt(difficulties.length)];
        }
    }

    public Category selectCategory() {
        Category[] categories = Category.values();

        Interface.print(messages.selectCategoryMessage(), true);

        String category = reader.read();

        try {
            int ans = Integer.parseInt(category);

            return categories[ans - 1];
        } catch (Exception e) {
            return categories[rand.nextInt(categories.length)];
        }
    }
}
