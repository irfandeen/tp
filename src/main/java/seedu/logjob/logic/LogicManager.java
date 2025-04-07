package seedu.logjob.logic;

import seedu.logjob.logic.commands.Command;
import seedu.logjob.logic.commands.CommandResult;
import seedu.logjob.logic.parser.ApplicationParser;
import seedu.logjob.storage.Storage;
import seedu.logjob.model.ApplicationManager;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;


public class LogicManager implements Logic {

    private final Storage storage;
    private final ApplicationManager model;
    private final ApplicationParser parser;
    private final UiMain ui;

    private boolean isRunning = true;

    public LogicManager(Storage storage, ApplicationManager model, UiMain ui) {
        this.storage = storage;
        this.model = model;
        this.ui = ui;
        this.parser = new ApplicationParser();
    }

    @Override
    public void execute(String commandText) throws IndexOutOfBoundsException, ParseException, EmptyTableException {
        ui.showLineBreak();

        // Execute Logic packages to get Command Result
        Command command = parser.parseCommand(commandText);
        CommandResult result = command.execute(model);

        // Read CommandResult output to print with ui
        ui.printMessage(result.getDisplayMessage());
        if (result.isExit()) {
            isRunning = false;
        }
        if (result.isHelp()) {
            ui.helpOutput();
        }
        if (result.getObservableList() != null) {
            ui.printApplications(result.getObservableList());
        }

        // Save application state to storage
        // TO BE IMPLEMENTED
    }

    public boolean getIsRunning() {
        return isRunning;
    }
}
