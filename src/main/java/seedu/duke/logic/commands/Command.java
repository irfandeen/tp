package seedu.duke.logic.commands;

import seedu.duke.logic.commands.exceptions.DeleteIndexOutOfBoundsException;
import seedu.duke.model.ApplicationManager;
import seedu.duke.ui.UiMain;
import seedu.duke.ui.exceptions.EmptyTableException;

public abstract class Command {
    public Command() {
    }
    public abstract void execute(ApplicationManager applicationManager, UiMain uiMain)
            throws DeleteIndexOutOfBoundsException, EmptyTableException;

    public boolean isRunning() {
        return true;
    }
}
