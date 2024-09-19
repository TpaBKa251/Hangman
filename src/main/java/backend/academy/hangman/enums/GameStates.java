package backend.academy.hangman.enums;

/**
 * Перечисление с именами всех состояний.
 * Состояния {@code EXIT} не существует.
 * При попытке найти это состояние, будет возвращаться {@code null}, и игра завершится
 */
public enum GameStates {
    WIN,
    LOSE,
    SETTINGS,
    PLAYING,
    EXIT
}
