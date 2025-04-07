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

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    public CommandResult execute(ApplicationManager applicationManager, UiMain ui)
            throws IndexOutOfBoundsException, EmptyTableException {
        ArrayList<InternshipApplication> applicationsFound = applicationManager.findApplications(searchTerm);
        if (applicationsFound.isEmpty()) {
            ui.printMessage("No applications found for search term: " + searchTerm);

        }

        ui.printMessage("Found " + applicationsFound.size() + " application(s) matching search term: " + searchTerm);
        ui.printApplications(applicationsFound);

        return null;
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
