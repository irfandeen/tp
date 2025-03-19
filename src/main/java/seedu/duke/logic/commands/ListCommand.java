package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute(ApplicationManager applicationManager) {
        try {
            applicationManager.listApplication();
        } catch (Exception e) {
            System.out.println("There are no applications in the list.");
        }
    }
}
