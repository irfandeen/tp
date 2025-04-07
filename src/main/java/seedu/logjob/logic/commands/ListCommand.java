package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_SUCCESS = "There are %d applications";

    public ListCommand() {
    }

    @Override
    public CommandResult execute(ApplicationManager applicationManager) throws EmptyTableException {
        applicationManager.listApplications();
        return new CommandResult(
                String.format(MESSAGE_LIST_SUCCESS, applicationManager.getSize()),
                false, false,
                applicationManager.getArrayList()
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ListCommand;
    }
}
