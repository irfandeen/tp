package seedu.duke.logic.commands;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
