package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int commandId;

    public DeleteCommand(int commandId) {
        this.commandId = commandId;
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) throws IndexOutOfBoundsException {
        if (commandId < 1 || commandId > applicationManager.getSize()) {
            throw new IndexOutOfBoundsException("Invalid index. Please enter a valid index in the list.");
        }
        applicationManager.deleteApplication(commandId, uiMain);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand otherCommand)) {
            return false;
        }

        return commandId == otherCommand.commandId;
    }
}
