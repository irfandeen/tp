package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.exceptions.EmptyTableException;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    private static final String MESSAGE_SUCCESS = "Applications successfully sorted by %s";
    private final String sortBy;

    public SortCommand(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public CommandResult execute(ApplicationManager applicationManager) throws EmptyTableException {
        applicationManager.sortApplication(this.sortBy);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, this.sortBy),
                false, false,
                applicationManager.getArrayList()
        );

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof SortCommand;
    }
}
