package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;

/**
 * Represents a command to sort internship applications based on a specified criterion.
 * The sorting is performed on a specific field, such as company name, job title, etc.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    private static final String MESSAGE_SUCCESS = "Applications successfully sorted by %s";
    private final String sortBy;


    /**
     * Creates a SortCommand with the specified sorting criterion.
     *
     * @param sortBy the field to sort the applications by (e.g., "company", "job title").
     */
    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Executes the sort command by sorting the applications according to the specified field.
     *
     * @param applicationManager the manager of the application data, used to perform sorting.
     * @return a {@link CommandResult} indicating the success of the sort operation and the sorted list.
     */
    @Override
    public CommandResult execute(ApplicationManager applicationManager) {
        applicationManager.sortApplication(this.sortBy);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, this.sortBy),
                false, false,
                applicationManager.getArrayList()
        );

    }

    /**
     * Compares this SortCommand to another object for equality.
     * Two SortCommands are considered equal if they sort by the same field.
     *
     * @param other the object to compare.
     * @return true if both commands are sorting by the same field; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof SortCommand;
    }
}
