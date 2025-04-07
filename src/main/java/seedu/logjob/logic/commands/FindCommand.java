package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String MESSAGE_FIND_SUCCESS = "%d Applications found";

    private final String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    public CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException {
        applicationManager.findApplications(searchTerm);
        return new CommandResult(
                String.format(MESSAGE_FIND_SUCCESS, applicationManager.getSize()),
                false, false,
                applicationManager.getArrayList()
        );
    }

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
