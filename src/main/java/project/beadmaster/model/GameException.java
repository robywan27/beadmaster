package project.beadmaster.model;

/**
 * This class customizes an exception specific for this game. This exception must be thrown whenever an error in the game occurs.
 */
public class GameException extends Exception {
    private static final long serialVersionUID = 1L;

    public GameException(String message) {
        super(message);
    }
}
