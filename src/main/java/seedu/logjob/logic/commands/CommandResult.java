package seedu.logjob.logic.commands;

import java.util.ArrayList;
import seedu.logjob.model.ReadOnlyApplication;

/*
    Encapsulates the result of Command Execution
 */
public class CommandResult {
    // Message to be displayed to user via UI
    private final String displayMessage;

    // Whether application should display help
    private final boolean isHelp;

    // Whether application should exit
    private final boolean isExit;

    // List of Applications to be displayed, can be null
    private final ArrayList<ReadOnlyApplication> observableList;

    /*
        Constructs a {@code CommandResult} with input fields
     */
    public CommandResult(String displayMessage,
                         boolean isHelp, boolean isExit,
                         ArrayList<ReadOnlyApplication> observableList) {
        this.displayMessage = displayMessage;
        this.isHelp = isHelp;
        this.isExit = isExit;
        this.observableList = observableList;
    }

    public CommandResult(String displayMessage, boolean isHelp, boolean isExit) {
        this(displayMessage, isHelp, isExit, null);
    }

    /*
    Getters for CommandResult attribute, class is read-only
     */

    public String getDisplayMessage() {
        return displayMessage;
    }

    public boolean isExit() {
        return isExit;
    }

    public ArrayList<ReadOnlyApplication> getObservableList() {
        return observableList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CommandResult otherCommandResult)) {
            return false;
        }

        return displayMessage.equals(otherCommandResult.displayMessage) &&
                isExit == otherCommandResult.isExit
                && observableList.equals(otherCommandResult.observableList);
    }

}
