package seedu.duke.main;

import seedu.duke.ui.UiMain;

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
