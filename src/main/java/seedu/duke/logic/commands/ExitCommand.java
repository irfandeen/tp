package seedu.duke.logic.commands;

import seedu.duke.main.Duke;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
    }

    @Override
    public void execute() {
        Duke.stopProgram();
    }
}
