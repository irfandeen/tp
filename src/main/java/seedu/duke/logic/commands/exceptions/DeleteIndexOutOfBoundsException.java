package seedu.duke.logic.commands.exceptions;

public class DeleteIndexOutOfBoundsException extends RuntimeException {
    public DeleteIndexOutOfBoundsException(String message) {
        super(message);
    }
}
