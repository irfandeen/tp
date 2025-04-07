package seedu.logjob.logic.parser.exceptions;

/**
 * Thrown when there is an error parsing user input.
 * This exception is typically used when the input does not match the expected format.
 */
public class ParseException extends Exception {
    /**
     * Constructs a new ParseException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public ParseException(String message) {
        super(message);
    }
}
