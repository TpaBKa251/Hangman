package backend.academy.hangman.enums;

import lombok.RequiredArgsConstructor;

/**
 * Перечисление со сложностями игры
 */
@RequiredArgsConstructor
public enum DifficultyEnum {
    EASY("ЛЕГКАЯ"),
    MEDIUM("СРЕДНЯЯ"),
    HARD("ВЫСОКАЯ");

    /**
     * Имя сложности на русском языке
     */
    private final String nameDifficulty;

    /**
     * Переопределенный метод {@code toString()}
     * @return имя сложности на русском языке
     */
    @Override
    public String toString() {
        return nameDifficulty;
    }
}
