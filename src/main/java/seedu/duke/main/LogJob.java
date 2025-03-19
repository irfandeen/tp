package seedu.duke.main;

import seedu.duke.ui.UiMain;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.ApplicationParser;
//I catch all Exceptions for easier debugging first next time change back
//import seedu.duke.logic.parser.exceptions.ParseException;

//Enable when storage is implemented
//import seedu.duke.storage.StorageManager;
import seedu.duke.model.ApplicationManager;

public class LogJob {

    private static Boolean isRunning = true;

    public static void main(String[] args) {
        //StorageManager storage = new StorageManager();
        ApplicationManager applicationManager = new ApplicationManager();
        UiMain uiMain = new UiMain();
        uiMain.introMessage();
        while (isRunning) {
            try {
                String input = uiMain.readInput();
                uiMain.showLineBreak();
                Command c = ApplicationParser.parseCommand(input);
                isRunning = c.isRunning();
                c.execute(applicationManager);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        uiMain.exitMessage();
    }
}
