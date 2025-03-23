package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.logic.commands.exceptions.DeleteIndexOutOfBoundsException;
import seedu.logjob.ui.UiMain;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int commandIndex;

    public DeleteCommand(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) throws DeleteIndexOutOfBoundsException {
        if (commandIndex < 0 || commandIndex >= applicationManager.getSize()) {
            throw new DeleteIndexOutOfBoundsException("Invalid index. Please enter a valid index in the list.");
        }
        applicationManager.deleteApplication(commandIndex, uiMain);
    }
}
