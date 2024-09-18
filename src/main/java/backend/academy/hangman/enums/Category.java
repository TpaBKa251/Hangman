package backend.academy.hangman.enums;

import lombok.RequiredArgsConstructor;

/**
 * Перечисление с категориями слов
 */
@RequiredArgsConstructor
public enum Category {
    ANIMALS("ЖИВОТНЫЕ"),
    FILMS("ФИЛЬМЫ");

    /**
     * Имя категории на русском языке
     */
    private final String nameCategory;

    /**
     * Переопределенный метод {@code toString()}
     * @return имя категории на русском языке
     */
    @Override
    public String toString() {
        return nameCategory;
    }
}
