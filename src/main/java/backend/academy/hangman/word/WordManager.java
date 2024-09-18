package backend.academy.hangman.word;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import java.security.SecureRandom;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *Класс для выбора случайного слова {@link Word} из словаря {@link Dictionary}
 * по заданным параметрам: сложности {@link DifficultyEnum} и категории {@link Category}
 */
@RequiredArgsConstructor
public class WordManager {
    private final SecureRandom random;

    /**
     * Метод, возвращающий случайное слово
     * @param difficultyEnum сложность
     * @param category категория слова
     * @return объект {@link Word}
     */
    public Word getWord(DifficultyEnum difficultyEnum, Category category) {
        Dictionary dictionary = Dictionary.DictionaryInitializer.initialize();

        List<Word> words = dictionary.dictionary().get(difficultyEnum).get(category);

        return words.get(random.nextInt(words.size()));
    }
}
