package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;
import seedu.duke.ui.UiMain;
import seedu.duke.ui.exceptions.EmptyTableException;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) throws EmptyTableException {
        applicationManager.listApplication(uiMain);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ListCommand;
    }
}
