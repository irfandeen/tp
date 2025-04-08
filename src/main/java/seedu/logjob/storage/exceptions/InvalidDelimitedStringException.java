package seedu.logjob.storage.exceptions;

/**
 * Exception thrown when a delimited string used to reconstruct an
 * {@link seedu.logjob.model.InternshipApplication} is malformed or invalid.
 * <p>
 * This typically occurs when the number of fields is incorrect or when parsing of
 * fields (such as date) fails.
 */
public class InvalidDelimitedStringException extends Exception {
    /**
     * Constructs an {@code InvalidDelimitedStringException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public InvalidDelimitedStringException(String message) {
        super(message);
    }
}
