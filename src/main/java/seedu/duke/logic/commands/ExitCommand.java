package seedu.duke.logic.commands;

import seedu.duke.main.Duke;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute() {
        Duke.stopProgram();
    }
}
