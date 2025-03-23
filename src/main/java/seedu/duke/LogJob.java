package seedu.duke;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;
import seedu.duke.storage.exceptions.StorageException;
import seedu.duke.ui.UiMain;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.ApplicationParser;

import seedu.duke.model.ApplicationManager;
import java.io.IOException;
import java.util.ArrayList;

public class LogJob {

    private static Boolean isRunning = true;

    public static void main(String[] args) {
        Storage storage = new StorageManager();
        UiMain uiMain = UiMain.getInstance();
        ArrayList<InternshipApplication> internships =  null;

        try {
            internships = storage.readApplicationsFromFile();
        } catch (IOException | StorageException | InvalidDelimitedStringException exception) {
            uiMain.handleError(exception);
        }

        assert internships != null;

        ApplicationManager applicationManager = new ApplicationManager(internships);

        uiMain.introMessage();
        while (isRunning) {
            try {
                String input = uiMain.readInput();
                uiMain.showLineBreak();
                Command command = ApplicationParser.parseCommand(input);
                isRunning = command.isRunning();
                command.execute(applicationManager, uiMain);
            } catch (Exception exception) {
                uiMain.handleError(exception);
            }
        }

        ArrayList<InternshipApplication> latestApplications = applicationManager.getArrayList();
        InternshipApplication[] applicationsArray = latestApplications.toArray(new InternshipApplication[0]);
        try {
            storage.storeApplicationsToFile(applicationsArray);
        } catch (StorageException exception) {
            uiMain.handleError(exception);
        }
        uiMain.exitMessage();
    }
}
