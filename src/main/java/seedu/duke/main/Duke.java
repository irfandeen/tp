package seedu.duke.main;

import seedu.duke.ui.UiMain;

public class Duke {

    private static Boolean isRunning = true;

    public static void stopProgram() {
        isRunning = false;
    }

    public static void main(String[] args) {
        UiMain.introMessage();
        while (isRunning) {
            UiMain.readInput();
        }
        UiMain.exitMessage();
    }
}
