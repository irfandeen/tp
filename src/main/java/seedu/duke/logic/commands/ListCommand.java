package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute() {
        ApplicationManager.listApplication();
    }
}
