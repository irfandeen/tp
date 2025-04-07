package seedu.logjob.logic.commands.exceptions;

/**
 * Thrown when an attempt is made to add a duplicate internship application.
 * This exception indicates that the application being added already exists in the system.
 */
public class DuplicateApplicationException extends RuntimeException {
    /**
     * Constructs a new DuplicateApplicationException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public DuplicateApplicationException(String message) {
        super(message);
    }
}
