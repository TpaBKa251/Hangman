package backend.academy.hangman.output;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerPreferencesMessagesTest {

    @Test
    public void testInitialize() {
        DifficultyEnum[] difficulties = DifficultyEnum.values();
        Category[] categories = Category.values();

        StringBuilder expectedDifficulties = new StringBuilder();
        expectedDifficulties.append("Выберите сложность, если хотите:\n");
        for (int i = 0; i < difficulties.length; i++) {
            expectedDifficulties
                .append('\t')
                .append(i + 1)
                .append(". ")
                .append(difficulties[i])
                .append('\n');
        }

        StringBuilder expectedCategories = new StringBuilder();
        expectedCategories.append("Выберите категорию, если хотите:\n");
        for (int i = 0; i < categories.length; i++) {
            expectedCategories
                .append('\t')
                .append(i + 1)
                .append(". ")
                .append(categories[i])
                .append('\n');
        }

        PlayerPreferencesMessages expectedMessages = new PlayerPreferencesMessages(
            expectedDifficulties.toString(),
            expectedCategories.toString()
        );

        PlayerPreferencesMessages actualMessages = PlayerPreferencesMessages.initialize();

        assertEquals(expectedMessages.selectDifficultyMessage(), actualMessages.selectDifficultyMessage());
        assertEquals(expectedMessages.selectCategoryMessage(), actualMessages.selectCategoryMessage());
    }

}
