package seedu.logjob.logic.commands.exceptions;

public class DeleteIndexOutOfBoundsException extends RuntimeException {
    public DeleteIndexOutOfBoundsException(String message) {
        super(message);
    }
}
