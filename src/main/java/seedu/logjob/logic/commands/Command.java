package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.exceptions.EmptyTableException;

public abstract class Command {
    public Command() {
    }
    public abstract CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException, EmptyTableException;

    public boolean isRunning() {
        return true;
    }

    @Override
    public abstract boolean equals(Object other);
}
