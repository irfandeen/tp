package seedu.duke.main;

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
        UiMain uiMain = new UiMain();
        ArrayList<InternshipApplication> internships =  null;

        try {
            internships = storage.readApplicationsFromFile();
        } catch (IOException | StorageException | InvalidDelimitedStringException e) {
            uiMain.print(e.getMessage());
        }

        assert internships != null;

        ApplicationManager applicationManager = new ApplicationManager(internships);

        uiMain.introMessage();
        while (isRunning) {
            try {
                String input = uiMain.readInput();
                uiMain.showLineBreak();
                Command c = ApplicationParser.parseCommand(input);
                isRunning = c.isRunning();
                c.execute(applicationManager);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        ArrayList<InternshipApplication> latestApplications = applicationManager.getArrayList();
        InternshipApplication[] applicationsArray = latestApplications.toArray(new InternshipApplication[0]);
        try {
            storage.storeApplicationsToFile(applicationsArray);
        } catch (StorageException e) {
            uiMain.print(e.getMessage());
        }
        uiMain.exitMessage();
    }
}
