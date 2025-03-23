package seedu.duke.logic.commands;

import seedu.duke.main.Constants;
import seedu.duke.model.ApplicationManager;
import seedu.duke.ui.UiMain;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(){}

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) {
        uiMain.helpOutput();
    }
}
