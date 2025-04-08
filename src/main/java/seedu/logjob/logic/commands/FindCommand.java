package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;

/**
 * Finds and filters internship applications by a search term.
 * Matches are  based on case-insensitive substring matching.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String MESSAGE_FIND_SUCCESS = "%d Applications found";

    private final String searchTerm;

    /**
     * Creates a FindCommand with the given search term.
     *
     * @param searchTerm the keyword to search for within applications.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    /**
     * Executes the find command by filtering applications that match the search term.
     *
     * @param applicationManager the application manager used to perform the search.
     * @return a {@link CommandResult} containing the result of the find operation.
     * @throws IndexOutOfBoundsException if any internal data inconsistency occurs (not expected here).
     */
    @Override
    public CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException {
        applicationManager.findApplications(searchTerm);
        return new CommandResult(
                String.format(MESSAGE_FIND_SUCCESS, applicationManager.getSize()),
                false, false,
                applicationManager.getArrayList()
        );
    }

    /**
     * Compares this FindCommand to another object for equality.
     * Two FindCommands are considered equal if their search terms match.
     *
     * @param other the object to compare.
     * @return true if both commands search for the same term; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindCommand)) {
            return false;
        }

        return searchTerm.equals(((FindCommand) other).searchTerm);
    }
}
