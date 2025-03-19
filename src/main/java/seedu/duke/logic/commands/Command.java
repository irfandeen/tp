package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;

public abstract class Command {
    public Command() {
    }
    public abstract void execute(ApplicationManager applicationManager);

    public boolean isRunning() {
        return true;
    }
}
