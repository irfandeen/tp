package seedu.duke.main;

import seedu.duke.ui.UiMain;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.ApplicationParser;
import seedu.duke.logic.parser.exceptions.ParseException;

public class Duke {

    private static Boolean isRunning = true;


    public static void main(String[] args) {
        UiMain.introMessage();
        while (isRunning) {
            try {
                String input = UiMain.readInput();
                UiMain.showLineBreak();
                Command c = ApplicationParser.parseCommand(input);
                // REMEMBER TO UNCOMMENT THESE 2 LINES ONCE PARSER IS IMPLEMENTED
                /*
                isRunning = c.isRunning();
                c.execute();
                */
            } catch (ParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        UiMain.exitMessage();
    }
}
