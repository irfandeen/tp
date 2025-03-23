package seedu.LogJob.logic.commands;

import seedu.LogJob.model.ApplicationManager;
import seedu.LogJob.ui.UiMain;

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
}
