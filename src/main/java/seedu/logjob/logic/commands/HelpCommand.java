package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;

/**
 * Represents a command to display help information to the user.
 * This command provides instructions or guidance about how to use the LogJob application.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Constructs a HelpCommand to display help information.
     */
    public HelpCommand(){}

    /**
     * Executes the help command by returning a {@link CommandResult} that triggers the display of help information.
     * Typically, this could show available commands or provide detailed usage instructions.
     *
     * @param applicationManager the manager of the application data (not used in this command).
     * @return a {@link CommandResult} indicating that the help information is displayed.
     */
    @Override
    public CommandResult execute(ApplicationManager applicationManager) {
        return new CommandResult("", true, false);
    }

    /**
     * Compares this HelpCommand to another object for equality.
     * HelpCommands are always equal to other HelpCommands.
     *
     * @param other the object to compare.
     * @return true if the other object is also a HelpCommand.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof HelpCommand;
    }
}
