package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;
import seedu.duke.ui.UiMain;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) {
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
