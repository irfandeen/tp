package seedu.LogJob.logic.commands.exceptions;

public class DeleteIndexOutOfBoundsException extends RuntimeException {
    public DeleteIndexOutOfBoundsException(String message) {
        super(message);
    }
}
