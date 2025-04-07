package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(){}

    @Override
    public CommandResult execute(ApplicationManager applicationManager, UiMain uiMain) {
        uiMain.helpOutput();
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof HelpCommand;
    }
}
