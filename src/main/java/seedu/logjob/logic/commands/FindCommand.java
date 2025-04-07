package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String searchTerm;
    private final String MESSAGE_FIND_SUCCESS = "%d Applications found";

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    public CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException, EmptyTableException {
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
