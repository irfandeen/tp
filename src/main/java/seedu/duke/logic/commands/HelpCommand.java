package seedu.duke.logic.commands;

import seedu.duke.main.Constants;

public class HelpCommand extends Command {
    public HelpCommand(){}

    @Override
    public void execute() {
        System.out.println(Constants.HELP_MESSAGE);

    }
}
