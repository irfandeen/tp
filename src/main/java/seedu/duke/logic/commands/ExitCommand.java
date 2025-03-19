package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
    }

    @Override
    public void execute(ApplicationManager applicationManager) {
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
