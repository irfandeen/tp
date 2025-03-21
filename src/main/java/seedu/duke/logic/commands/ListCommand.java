package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;
import seedu.duke.ui.exceptions.EmptyTableException;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute (ApplicationManager applicationManager) throws EmptyTableException {
        try {
            applicationManager.listApplication();
        } catch (EmptyTableException e) {
            throw e;
        }
    }
}
