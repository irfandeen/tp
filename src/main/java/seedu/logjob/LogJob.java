package seedu.logjob;

import seedu.logjob.model.InternshipApplication;
import seedu.logjob.storage.Storage;
import seedu.logjob.storage.StorageManager;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;
import seedu.logjob.storage.exceptions.StorageException;
import seedu.logjob.ui.UiMain;
import seedu.logjob.logic.commands.Command;
import seedu.logjob.logic.parser.ApplicationParser;

import seedu.logjob.model.ApplicationManager;

import java.io.IOException;
import java.util.ArrayList;

public class LogJob {
    private Boolean isRunning = true;
    private ApplicationParser parser;
    private Storage storage;
    private UiMain ui;
    private ApplicationManager applicationManager;

    private LogJob() {
        parser = new ApplicationParser();
        storage = new StorageManager();
        ui = UiMain.getInstance();

        ArrayList<InternshipApplication> internships = null;
        try {
            internships = storage.readApplicationsFromFile();
        } catch (IOException | StorageException | InvalidDelimitedStringException exception) {
            ui.handleError(exception);
        }

        applicationManager = new ApplicationManager(internships);
    }

    public void run() {
        ui.introMessage();
        while (isRunning) {
            try {
                String input = ui.readInput();
                ui.showLineBreak();
                Command command = parser.parseCommand(input);
                isRunning = command.isRunning();
                command.execute(applicationManager, ui);
            } catch (Exception exception) {
                ui.handleError(exception);
            }
        }
        exit();
    }

    private void exit() {
        ArrayList<InternshipApplication> internships = applicationManager.getArrayList();
        InternshipApplication[] applicationsArray = internships.toArray(new InternshipApplication[0]);
        try {
            storage.storeApplicationsToFile(applicationsArray);
        } catch (StorageException exception) {
            ui.handleError(exception);
        }
        ui.exitMessage();
    }

    public static void main(String[] args) {
        LogJob logJob = new LogJob();
        logJob.run();
    }
}
