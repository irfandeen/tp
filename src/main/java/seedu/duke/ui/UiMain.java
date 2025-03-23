package seedu.duke.ui;

import java.util.Scanner;
import java.util.ArrayList;

import seedu.duke.main.Constants;
import seedu.duke.model.InternshipApplication;
import seedu.duke.ui.exceptions.EmptyTableException;

public class UiMain {
    private final Scanner scan;

    public UiMain() {
        this.scan = new Scanner(System.in);
    }

    public void printApplications(ArrayList<ArrayList<String>> applications) throws EmptyTableException {
        String table = UiTable.getTable(applications);
        System.out.println(table);
    }

    public void addSucceedOutput(InternshipApplication application) {
        System.out.println(
                "Application: "
                        + application.getCompanyName()
                        + " "
                        + application.getJobTitle()
                        + " Added Successfully");
    }

    public void deleteSucceedOutput(int index){
        System.out.println("Index: " + index + " Succeeds Deletion");
    }

    public void helpOutput() {
        System.out.println(Constants.HELP_MESSAGE);
    }

    public void introMessage() {
        System.out.println(Constants.LOGO);
        System.out.println(Constants.INTRO_MESSAGE);
    }

    public String readInput() {
        String input = "INVALID";
        if (scan.hasNextLine()) {
            input = scan.nextLine();
        }
        return input;
    }

    public void handleError(Exception error) {
        System.out.println(error.getMessage());
    }

    public void showLineBreak() {
        System.out.println(Constants.LINE_BREAK);
    }

    public void exitMessage() {
        System.out.println("Goodbye!");
        scan.close();
    }
}
