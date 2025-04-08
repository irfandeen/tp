package seedu.logjob.model.exceptions;

/**
 * Exception thrown when an invalid application status is encountered.
 */
public class InvalidApplicationStatus extends Exception {
    /**
     * Constructs an InvalidApplicationStatus exception with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public InvalidApplicationStatus(String message) {
        super(message);
    }
}
