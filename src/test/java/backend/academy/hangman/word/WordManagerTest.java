package backend.academy.hangman.word;

import backend.academy.hangman.enums.Category;
import backend.academy.hangman.enums.DifficultyEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Тесты класса WordManager для получения случайного слова")
@ExtendWith(MockitoExtension.class)
public class WordManagerTest {

    @Mock
    private SecureRandom random;

    @InjectMocks
    private WordManager wordManager;

    @DisplayName("Тест получения случайного слова легкой сложности категории животные")
    @Test
    public void testEasyAnimals() {
        Word expectedWord = new Word("КОТ", "Самое популярное домашнее животное");
        DifficultyEnum difficultyEnum = DifficultyEnum.EASY;
        Category category = Category.ANIMALS;

        when(random.nextInt(3)).thenReturn(0);

        Word actualWord = wordManager.getWord(difficultyEnum, category);

        assertEquals(expectedWord.word(), actualWord.word());
        assertEquals(expectedWord.help(), actualWord.help());
    }

    @DisplayName("Тест получения случайного слова средней сложности категории фильмы")
    @Test
    public void testMediumFilms() {
        Word expectedWord = new Word("ТЕРМИНАТОР", "Фильм про борьбу человечества с машинами");
        DifficultyEnum difficultyEnum = DifficultyEnum.MEDIUM;
        Category category = Category.FILMS;

        when(random.nextInt(3)).thenReturn(1);

        Word actualWord = wordManager.getWord(difficultyEnum, category);

        assertEquals(expectedWord.word(), actualWord.word());
        assertEquals(expectedWord.help(), actualWord.help());
    }

    @DisplayName("Тест получения случайного слова высокой сложности категории животные")
    @Test
    public void testHardAnimals() {
        Word expectedWord = new Word("АВСТРАЛИЙСКАЯ ЕХИДНА", "Перевернутый антагонист Соника");
        DifficultyEnum difficultyEnum = DifficultyEnum.HARD;
        Category category = Category.ANIMALS;

        when(random.nextInt(3)).thenReturn(2);

        Word actualWord = wordManager.getWord(difficultyEnum, category);

        assertEquals(expectedWord.word(), actualWord.word());
        assertEquals(expectedWord.help(), actualWord.help());
    }
}
