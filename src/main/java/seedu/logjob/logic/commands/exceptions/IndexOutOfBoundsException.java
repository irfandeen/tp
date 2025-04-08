package seedu.logjob.logic.commands.exceptions;

/**
 * Thrown when an index is out of the valid bounds in a list or collection.
 * This exception is typically used when the provided index is invalid or out of range.
 */
public class IndexOutOfBoundsException extends RuntimeException {
    /**
     * Constructs a new IndexOutOfBoundsException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
