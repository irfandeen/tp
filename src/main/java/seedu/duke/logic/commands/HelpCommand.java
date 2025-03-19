package seedu.duke.logic.commands;

import seedu.duke.main.Constants;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(){}

    @Override
    public void execute() {
        System.out.println(Constants.HELP_MESSAGE);

    }
}
