package backend.academy.hangman.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {
    ANIMALS("ЖИВОТНЫЕ"),
    FILMS("ФИЛЬМЫ");

    private final String nameCategory;

    @Override
    public String toString() {
        return nameCategory;
    }
}

/*
feat: добавлено перечисление Category

Добавлено перечисление Category, которое содержит категории слов.
 */
