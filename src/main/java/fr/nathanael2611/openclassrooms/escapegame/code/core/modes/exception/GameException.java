package fr.nathanael2611.openclassrooms.escapegame.code.core.modes.exception;

/**
 * A simple exception that will be thrown when there is a problem in a Mode party
 */
public class GameException extends Exception
{
    public GameException(String message)
    {
        super(message);
    }
}
