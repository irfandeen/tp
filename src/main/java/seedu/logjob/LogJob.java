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
