package seedu.duke.main;

import seedu.duke.model.ApplicationStatus;
import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;
import seedu.duke.storage.exceptions.StorageException;
import seedu.duke.ui.UiMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static Boolean isRunning = true;

    public static void stopProgram() {
        isRunning = false;
    }

    // For transient testing - will be replaced before v1.0
    public static void main(String[] args) {
        UiMain.introMessage();

        Storage storage = new StorageManager();
        InternshipApplication[] previousApplications = null;
        try {
            previousApplications = storage.readApplicationsFromFile();
        } catch (IOException e) {
            System.out.println("IO Exception");
            return;
        } catch (StorageException e) {
            System.out.println("Storage Exception");
            return;
        } catch (InvalidDelimitedStringException e) {
            System.out.println("Invalid delimited string: " + e.getMessage());
            return;
        }

        ArrayList<InternshipApplication> applicationsList = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            applicationsList.add(new InternshipApplication("google", "engineer", ApplicationStatus.REJECTED));
        }

        for (InternshipApplication application : previousApplications) {
            applicationsList.add(application);
        }

        try {
            storage.storeApplicationsToFile(applicationsList.toArray(new InternshipApplication[0]));
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }

        UiMain.exitMessage();
    }
}
