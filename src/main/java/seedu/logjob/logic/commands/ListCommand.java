package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;

/**
 * Represents a command to list all internship applications currently stored.
 * This command outputs the total number of applications and returns the full list of applications.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_SUCCESS = "There are %d applications";

    /**
     * Represents a command to list all internship applications currently stored.
     * This command outputs the total number of applications and returns the full list of applications.
     */
    public ListCommand() {
    }

    /**
     * Executes the list command by listing all internship applications stored in the system.
     * This method returns a {@link CommandResult} containing the number of applications and the list.
     *
     * @param applicationManager the manager of the application data, used to retrieve the list of applications.
     * @return a {@link CommandResult} containing the number of applications and the list of applications.
     */
    @Override
    public CommandResult execute(ApplicationManager applicationManager) {
        applicationManager.listApplications();
        return new CommandResult(
                String.format(MESSAGE_LIST_SUCCESS, applicationManager.getSize()),
                false, false,
                applicationManager.getArrayList()
        );
    }

    /**
     * Compares this ListCommand to another object for equality.
     * ListCommands are always equal to other ListCommands.
     *
     * @param other the object to compare.
     * @return true if the other object is also a ListCommand.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ListCommand;
    }
}
