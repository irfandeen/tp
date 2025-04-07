package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.DuplicateApplicationException;
import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;

public abstract class Command {
    public Command() {
    }
    public abstract CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException, DuplicateApplicationException;

    public boolean isRunning() {
        return true;
    }

    @Override
    public abstract boolean equals(Object other);
}
