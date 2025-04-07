package seedu.logjob.storage.exceptions;

/**
 * Exception thrown when an error occurs during file storage operations,
 * such as reading from, writing to, or creating the storage file.
 */
public class StorageException extends Exception {
    /**
     * Constructs a {@code StorageException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public StorageException(String message) {
        super(message);
    }
}
