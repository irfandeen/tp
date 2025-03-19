package seedu.duke.logic.commands;

import seedu.duke.main.Constants;
import seedu.duke.model.ApplicationManager;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(){}

    @Override
    public void execute(ApplicationManager applicationManager) {
        System.out.println(Constants.HELP_MESSAGE);

    }
}
