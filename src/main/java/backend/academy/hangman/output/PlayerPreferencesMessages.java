package backend.academy.hangman.output;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;

/**
 * Record, содержащий сообщения, выводящиеся при выборе пользователем сложности игры и категории слов
 * @param selectDifficultyMessage сообщение о выборе сложности
 * @param selectCategoryMessage сообщение о выборе категории
 */
public record PlayerPreferencesMessages(
    String selectDifficultyMessage,
    String selectCategoryMessage
) {
    /**
     * Метод инициализации сообщений. Сообщения создаются автоматически на основе существующих сложностей и категорий
     * @return объект {@link PlayerPreferencesMessages}
     */
    public static PlayerPreferencesMessages initialize() {
        StringBuilder difficulties = new StringBuilder();
        difficulties.append("Выберите сложность, если хотите:\n");
        StringBuilder categories = new StringBuilder();
        categories.append("Выберите категорию, если хотите:\n");

        for (int i = 0; i < DifficultyEnum.values().length; i++) {
            difficulties
                .append('\t')
                .append(i + 1)
                .append(". ")
                .append(DifficultyEnum.values()[i])
                .append('\n');
        }

        for (int i = 0; i < Category.values().length; i++) {
            categories
                .append('\t')
                .append(i + 1)
                .append(". ")
                .append(Category.values()[i])
                .append('\n');
        }

        return new PlayerPreferencesMessages(difficulties.toString(), categories.toString());
    }
}
