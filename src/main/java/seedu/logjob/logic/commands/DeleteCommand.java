package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int commandIndex;

    public DeleteCommand(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    @Override
    public CommandResult execute(ApplicationManager applicationManager, UiMain uiMain)
            throws IndexOutOfBoundsException {
        if (commandIndex <= 0 || commandIndex > applicationManager.getSize()) {
            throw new IndexOutOfBoundsException("Invalid index. Please enter a valid index in the list.");
        }
        applicationManager.deleteApplication(commandIndex, uiMain);
        return null;
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
