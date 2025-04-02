package seedu.logjob.ui;

import java.util.Scanner;
import java.util.ArrayList;

import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.exceptions.EmptyTableException;

public class UiMain {
    private static UiMain ui = null;
    private final Scanner scan = new Scanner(System.in);

    private UiMain() {}

    public static UiMain getInstance() {
        if(ui == null) {
            ui = new UiMain();
        }
        return ui;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printApplications(ArrayList<InternshipApplication> applicationList) throws EmptyTableException {
        assert applicationList != null : "Data should not be null";
        String table = UiTable.getTable(applicationList);
        System.out.println(table);
    }

    public void helpOutput() {
        System.out.println(UiConstants.HELP_MESSAGE);
    }

    public void introMessage() {
        System.out.println(UiConstants.LOGO);
        System.out.println(UiConstants.INTRO_MESSAGE);
    }

    public String readInput() {
        String input = "INVALID";
        if (scan.hasNextLine()) {
            input = scan.nextLine();
        }
        return input;
    }

    public void handleError(Exception error) {
        assert error != null : "Error should not be null";
        System.out.println(error.getMessage());
    }

    public void showLineBreak() {
        System.out.println(UiConstants.LINE_BREAK);
    }

    public void exitMessage() {
        System.out.println("Goodbye!");
        scan.close();
    }
}
