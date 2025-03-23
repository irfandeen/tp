package seedu.LogJob.logic.commands;

import seedu.LogJob.logic.commands.exceptions.DeleteIndexOutOfBoundsException;
import seedu.LogJob.model.ApplicationManager;
import seedu.LogJob.ui.UiMain;
import seedu.LogJob.ui.exceptions.EmptyTableException;

public abstract class Command {
    public Command() {
    }
    public abstract void execute(ApplicationManager applicationManager, UiMain uiMain)
            throws DeleteIndexOutOfBoundsException, EmptyTableException;

    public boolean isRunning() {
        return true;
    }
}
