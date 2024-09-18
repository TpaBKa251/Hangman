package backend.academy.hangman.setting;

import backend.academy.hangman.enums.Category;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.SecureRandom;

import backend.academy.hangman.enums.DifficultyEnum;
import backend.academy.hangman.input.Reader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerPreferencesTest {
    @Mock
    private SecureRandom random;

    @Mock
    private Reader reader;

    @InjectMocks
    private PlayerPreferences playerPreferences;

    private PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testSelectEasyDifficulty() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.EASY;

        when(reader.read()).thenReturn("1");

        DifficultyEnum actualDifficulty = playerPreferences.selectDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }

    @Test
    public void testSelectMediumDifficulty() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.MEDIUM;

        when(reader.read()).thenReturn("2");

        DifficultyEnum actualDifficulty = playerPreferences.selectDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }

    @Test
    public void testSelectHardDifficulty() {
        DifficultyEnum expectedDifficulty = DifficultyEnum.HARD;

        when(reader.read()).thenReturn("3");

        DifficultyEnum actualDifficulty = playerPreferences.selectDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "4", "\n", "w"})
    public void testSelectRandomDifficulty(String input) {
        DifficultyEnum expectedDifficulty = DifficultyEnum.MEDIUM;

        when(reader.read()).thenReturn(input);
        when(random.nextInt(3)).thenReturn(1);

        DifficultyEnum actualDifficulty = playerPreferences.selectDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }


    @Test
    public void testSelectAnimalsCategory() {
        Category expectedCategory = Category.ANIMALS;

        when(reader.read()).thenReturn("1");

        Category actualCategory = playerPreferences.selectCategory();

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void testSelectFilmsCategory() {
        Category expectedCategory = Category.FILMS;

        when(reader.read()).thenReturn("2");

        Category actualCategory = playerPreferences.selectCategory();

        assertEquals(expectedCategory, actualCategory);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "4", "\n", "w"})
    public void testSelectRandomCategory(String input) {
        Category expectedCategory = Category.ANIMALS;

        when(reader.read()).thenReturn(input);
        when(random.nextInt(2)).thenReturn(0);

        Category actualCategory = playerPreferences.selectCategory();

        assertEquals(expectedCategory, actualCategory);
    }
}
