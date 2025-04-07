package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_DELETE_SUCCESS = "ID: %d Deleted Successfully";
    private static final String MESSAGE_OUT_OF_BOUNDS = "Invalid index. Please enter a valid index in the list.";

    private final int commandIndex;

    public DeleteCommand(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    @Override
    public CommandResult execute(ApplicationManager applicationManager, UiMain uiMain)
            throws IndexOutOfBoundsException {
        if (commandIndex <= 0 || commandIndex > applicationManager.getSize()) {
            throw new IndexOutOfBoundsException(MESSAGE_OUT_OF_BOUNDS);
        }
      
        applicationManager.deleteApplication(commandIndex);
      
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, commandIndex), false, false);
    }

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
