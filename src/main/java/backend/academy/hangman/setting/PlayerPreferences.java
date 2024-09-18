package backend.academy.hangman.setting;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.input.Reader;
import backend.academy.hangman.output.Interface;
import backend.academy.hangman.output.PlayerPreferencesMessages;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;

/**
 * Класс для настройки первичных настроек игры (предпочтения пользователя).
 * Настраивается сложность {@link DifficultyEnum} и категория {@link Category}
 */
@RequiredArgsConstructor
public class PlayerPreferences {
    private final Reader reader;
    private final SecureRandom rand;
    /**
     * Сообщения, выводящиеся пользователю {@link PlayerPreferencesMessages}
     */
    private final PlayerPreferencesMessages messages = PlayerPreferencesMessages.initialize();

    /**
     * Метод выбора сложности игры. Если пользователь введет сложность,
     * которой нет или любой другой символ (в том числе ничего), будет выбрана случайная сложность
     * @return элемент перечисления {@link DifficultyEnum}
     */
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

    /**
     * Метод выбора категории слова. Если пользователь введет категорию,
     * которой нет или любой другой символ (в том числе ничего), будет выбрана случайная категория
     * @return элемент перечисления {@link Category}
     */
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
