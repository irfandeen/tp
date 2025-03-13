package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;

public class DeleteCommand extends Command {
    private final int commandIndex;

    public DeleteCommand(int commandIndex) {
        this.commandIndex = commandIndex;
    }

    @Override
    public void execute() {
        ApplicationManager.deleteApplication(commandIndex);
    }
}
