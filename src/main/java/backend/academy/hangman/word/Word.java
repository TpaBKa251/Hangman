package backend.academy.hangman.word;

/**
 * Record для хранения слова и подсказки к нему
 * @param word слово
 * @param help подсказка
 */
public record Word(String word, String help) {
}
