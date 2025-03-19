package seedu.duke.logic.commands;

public abstract class Command {
    public Command() {
    }
    public abstract void execute();

    public boolean isRunning() {
        return true;
    }
}
