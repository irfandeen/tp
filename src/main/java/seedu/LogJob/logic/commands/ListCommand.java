package seedu.LogJob.logic.commands;

import seedu.LogJob.model.ApplicationManager;
import seedu.LogJob.ui.UiMain;
import seedu.LogJob.ui.exceptions.EmptyTableException;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) throws EmptyTableException {
        applicationManager.listApplication(uiMain);
    }
}
