package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
    }

    @Override
    public CommandResult execute(ApplicationManager applicationManager) {
        return null;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ExitCommand;
    }
}
