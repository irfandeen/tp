package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    private final String sortBy;

    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) throws EmptyTableException {
        applicationManager.sortApplication(this.sortBy, uiMain);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof SortCommand;
    }
}
