package backend.academy.hangman.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum DifficultyEnum {
    EASY("ЛЕГКАЯ"),
    MEDIUM("СРЕДНЯЯ"),
    HARD("ВЫСОКАЯ");

    private final String nameDifficulty;

    @Override
    public String toString() {
        return nameDifficulty;
    }
}

/*
feat: добавлено перечисление DifficultyEnum

Добавлено перечисление DifficultyEnum, которое содержит уровни сложности игры.
 */
