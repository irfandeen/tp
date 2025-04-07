package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;

/**
 * Represents a command to exit the LogJob application.
 * This command signals to stop execution.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    /**
     * Constructs an {@code ExitCommand}.
     */
    public ExitCommand() {
    }

    /**
     * Executes the exit command, which returns a {@link CommandResult}
     * indicating that the application should terminate.
     *
     * @param applicationManager the manager of the application's data (not used here).
     * @return a {@code CommandResult} indicating an exit is requested.
     */
    @Override
    public CommandResult execute(ApplicationManager applicationManager) {
        return new CommandResult("", false, true);
    }

    /**
     * Indicates that this command should stop the application.
     *
     * @return {@code false}, signaling the end of the application loop.
     */
    @Override
    public boolean isRunning() {
        return false;
    }

    /**
     * Compares this ExitCommand with another object for equality.
     * ExitCommands are always equal to other ExitCommands.
     *
     * @param other the object to compare.
     * @return true if the other object is also an ExitCommand.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ExitCommand;
    }
}
