package seedu.LogJob.logic.commands;

import seedu.LogJob.model.ApplicationManager;
import seedu.LogJob.ui.UiMain;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(){}

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) {
        uiMain.helpOutput();
    }
}
