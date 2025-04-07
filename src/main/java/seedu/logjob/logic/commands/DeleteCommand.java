package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;

/**
 * Represents a command to delete an internship application from the list
 * based on a user-specified index.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_DELETE_SUCCESS = "ID: %d Deleted Successfully";
    private static final String MESSAGE_OUT_OF_BOUNDS = "Invalid index. Please enter a valid index in the list.";

    private final int commandIndex;

    /**
     * Creates a DeleteCommand with the specified index.
     *
     * @param commandIndex the 1-based index of the application to be deleted.
     */
    public DeleteCommand(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    /**
     * Executes the delete command by removing the application at the given index.
     *
     * @param applicationManager the manager responsible for handling application data.
     * @return the result of the command execution.
     * @throws IndexOutOfBoundsException if the index is invalid (not within list bounds).
     */
    @Override
    public CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException {
        if (commandIndex <= 0 || commandIndex > applicationManager.getSize()) {
            throw new IndexOutOfBoundsException(MESSAGE_OUT_OF_BOUNDS);
        }

        applicationManager.deleteApplication(commandIndex);

        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, commandIndex), false, false);
    }

    /**
     * Compares this DeleteCommand with another object for equality.
     * Two DeleteCommands are considered equal if they have the same target index.
     *
     * @param other the object to compare to.
     * @return true if the other object is a DeleteCommand with the same index; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand otherCommand)) {
            return false;
        }

        return commandIndex == otherCommand.commandIndex;
    }
}
