package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;


public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public HelpCommand(){}

    @Override
    public CommandResult execute(ApplicationManager applicationManager) {
        return new CommandResult("", true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof HelpCommand;
    }
}
