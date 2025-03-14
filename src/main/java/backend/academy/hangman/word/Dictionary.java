package backend.academy.hangman.word;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Record для категоризированного хранения слов {@link Word}
 * по сложностям {@link DifficultyEnum} и категориям {@link Category}
 * @param dictionary {@link HashMap} с ключом {@code DifficultyEnum} и значением {@code HashMap},
 *                                 в которой ключ - {@code Category}, значение - {@link List} из слов {@code Word}
 */
public record Dictionary(HashMap<DifficultyEnum, HashMap<Category, List<Word>>> dictionary) {
    /**
     * Вложенный статический класс для инициализации словаря
     */
    static class DictionaryInitializer {

        /**
         * Статический метод, создающий заполненный словарь
         * @return объект {@link Dictionary}
         */
        public static Dictionary initialize() {
            HashMap<DifficultyEnum, HashMap<Category, List<Word>>> dictionary = new HashMap<>();

            dictionary.put(DifficultyEnum.EASY, new HashMap<>());
            dictionary.put(DifficultyEnum.MEDIUM, new HashMap<>());
            dictionary.put(DifficultyEnum.HARD, new HashMap<>());

            dictionary.get(DifficultyEnum.EASY).put(Category.ANIMALS, Arrays.asList(
                new Word("КОТ", "Самое популярное домашнее животное"),
                new Word("СОБАКА", "Верное и преданное домашнее животное"),
                new Word("СИНИЦА", "Маленькая птица")));
            dictionary.get(DifficultyEnum.EASY).put(Category.FILMS, Arrays.asList(
                new Word("ВРЕМЯ", "Валюта в этом фильме - время"),
                new Word("ЧУЖОЙ", "Фильм про борьбу с инопланетянином"),
                new Word("АВАТАР", "Аанг.")));

            dictionary.get(DifficultyEnum.MEDIUM).put(Category.ANIMALS, Arrays.asList(
                new Word("КАПИБАРА", "Самое мемное животное"),
                new Word("ДЕЛЬФИН", "Морское млекопитающее"),
                new Word("ВЫХУХОЛЬ", "Похож на крота, но название смешное")));
            dictionary.get(DifficultyEnum.MEDIUM).put(Category.FILMS, Arrays.asList(
                new Word("ГЛАДИАТОР", "Боец в Древнем Риме"),
                new Word("ТЕРМИНАТОР", "Фильм про борьбу человечества с машинами"),
                new Word("МСТИТЕЛИ", "Фильм про команду супер-героев")));

            dictionary.get(DifficultyEnum.HARD).put(Category.ANIMALS, Arrays.asList(
                new Word("АКСОЛОТЛЬ", "Способно отрастить любую потерянную конечность"),
                new Word("ВЕЛОЦЕРАПТОР", "Хищный динозавр"),
                new Word("АВСТРАЛИЙСКАЯ ЕХИДНА", "Перевернутый антагонист Соника")));
            dictionary.get(DifficultyEnum.HARD).put(Category.FILMS, Arrays.asList(
                new Word("ОППЕНГЕЙМЕР", "Фильм про создание бомбы"),
                new Word("ЧЕЛОВЕК-ПАУК", "Фильм про самого популярного супер-героя"),
                new Word("ЗВЕЗДНЫЕ ВОЙНЫ", "Космическая фантастика")));

            return new Dictionary(dictionary);
        }
    }
}
