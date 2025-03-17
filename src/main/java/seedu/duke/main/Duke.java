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

        UiMain.exitMessage();
    }
}
