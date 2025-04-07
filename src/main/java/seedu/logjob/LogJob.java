package seedu.logjob;

import seedu.logjob.logic.LogicManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.storage.Storage;
import seedu.logjob.storage.StorageManager;
import seedu.logjob.ui.UiMain;
import seedu.logjob.logic.parser.ApplicationParser;
import seedu.logjob.logic.Logic;
import seedu.logjob.model.ApplicationManager;

import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;
import seedu.logjob.storage.exceptions.StorageException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Entry point for the LogJob application.
 * Manages the lifecycle of the application, including initialization,
 * input handling, and application termination.
 */
public class LogJob {
    private Boolean isRunning = true;
    private ApplicationParser parser;
    private Storage storage;
    private UiMain ui;
    private ApplicationManager applicationManager;
    private Logic logic;

    /**
     * Private constructor that initializes all necessary components of the application.
     * Handles file loading and error recovery if any issues occur during startup.
     */
    private LogJob() {
        storage = new StorageManager();
        ui = UiMain.getInstance();

        ui.introMessage();
        ArrayList<InternshipApplication> internships = null;
        try {
            internships = storage.readFromFile();
        } catch (InvalidDelimitedStringException | StorageException exception) {
            ui.showLineBreak();
            ui.handleError(exception);
            ui.showLineBreak();
            internships = new ArrayList<>();
        } catch (IOException exception) {
            ui.handleError(exception);
        }

        applicationManager = new ApplicationManager(internships);
        logic = new LogicManager(storage, applicationManager, ui);
    }

    /**
     * Starts the main loop of the application.
     * Continuously reads and processes user input until the user exits.
     */
    public void run() {
        while (isRunning) {
            try {
                String input = ui.readInput();
                logic.execute(input);
                isRunning = logic.getIsRunning();
            } catch (Exception exception) {
                ui.handleError(exception);
            }
        }

        ui.exitMessage();
    }

    /**
     * Main method. Creates and runs a new instance of the LogJob application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        LogJob logJob = new LogJob();
        logJob.run();
    }
}
