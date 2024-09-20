package backend.academy.hangman.word;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты словаря record Dictionary")
public class DictionaryTest {

    private Dictionary dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = Dictionary.DictionaryInitializer.initialize();
    }

    @DisplayName("Тест инициализации словаря")
    @Test
    public void testInitialize() {
        assertThat(dictionary.dictionary()).isNotNull();
    }

    @DisplayName("Тест, проверяющий наличие всех категорий")
    @ParameterizedTest
    @EnumSource(DifficultyEnum.class)
    public void testContainsCategories(DifficultyEnum difficulty) {
        HashMap<Category, List<Word>> wordsByCategory = dictionary.dictionary().get(difficulty);

        assertThat(wordsByCategory).isNotNull();
        assertThat(wordsByCategory.keySet()).containsExactlyInAnyOrder(Category.ANIMALS, Category.FILMS);
    }

    @DisplayName("Тест, проверяющий наличие всех сложностей")
    @Test
    public void testContainsDifficulties() {
        assertThat(dictionary.dictionary().keySet())
            .containsExactlyInAnyOrder(DifficultyEnum.EASY, DifficultyEnum.MEDIUM, DifficultyEnum.HARD);
    }

    @DisplayName("Тест, проверяющий наличие всех слов всех категорий для легкой сложности")
    @Test
    public void testEasyDifficulty() {
        HashMap<Category, List<Word>> wordsByCategory = dictionary.dictionary().get(DifficultyEnum.EASY);

        assertThat(wordsByCategory.get(Category.ANIMALS)).containsExactlyInAnyOrder(
            new Word("КОТ", "Самое популярное домашнее животное"),
            new Word("СОБАКА", "Верное и преданное домашнее животное"),
            new Word("СИНИЦА", "Маленькая птица")
        );

        assertThat(wordsByCategory.get(Category.FILMS)).containsExactlyInAnyOrder(
            new Word("ВРЕМЯ", "Валюта в этом фильме - время"),
            new Word("ЧУЖОЙ", "Фильм про борьбу с инопланетянином"),
            new Word("АВАТАР", "Аанг.")
        );
    }

    @DisplayName("Тест, проверяющий наличие всех слов всех категорий для средней сложности")
    @Test
    public void testMediumDifficulty() {
        HashMap<Category, List<Word>> wordsByCategory = dictionary.dictionary().get(DifficultyEnum.MEDIUM);

        assertThat(wordsByCategory.get(Category.ANIMALS)).containsExactlyInAnyOrder(
            new Word("КАПИБАРА", "Самое мемное животное"),
            new Word("ДЕЛЬФИН", "Морское млекопитающее"),
            new Word("ВЫХУХОЛЬ", "Похож на крота, но название смешное")
        );

        assertThat(wordsByCategory.get(Category.FILMS)).containsExactlyInAnyOrder(
            new Word("ГЛАДИАТОР", "Боец в Древнем Риме"),
            new Word("ТЕРМИНАТОР", "Фильм про борьбу человечества с машинами"),
            new Word("МСТИТЕЛИ", "Фильм про команду супер-героев")
        );
    }

    @DisplayName("Тест, проверяющий наличие всех слов всех категорий для высокой сложности")
    @Test
    public void testHardDifficulty() {
        HashMap<Category, List<Word>> wordsByCategory = dictionary.dictionary().get(DifficultyEnum.HARD);

        assertThat(wordsByCategory.get(Category.ANIMALS)).containsExactlyInAnyOrder(
            new Word("АКСОЛОТЛЬ", "Способно отрастить любую потерянную конечность"),
            new Word("ВЕЛОЦЕРАПТОР", "Хищный динозавр"),
            new Word("АВСТРАЛИЙСКАЯ ЕХИДНА", "Перевернутый антагонист Соника")
        );

        assertThat(wordsByCategory.get(Category.FILMS)).containsExactlyInAnyOrder(
            new Word("ОППЕНГЕЙМЕР", "Фильм про создание бомбы"),
            new Word("ЧЕЛОВЕК-ПАУК", "Фильм про самого популярного супер-героя"),
            new Word("ЗВЕЗДНЫЕ ВОЙНЫ", "Космическая фантастика")
        );
    }
}
