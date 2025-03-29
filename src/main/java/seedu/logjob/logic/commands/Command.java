package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

public abstract class Command {
    public Command() {
    }
    public abstract void execute(ApplicationManager applicationManager, UiMain uiMain)
            throws IndexOutOfBoundsException, EmptyTableException;

    public boolean isRunning() {
        return true;
    }

    @Override
    public abstract boolean equals(Object other);
}
