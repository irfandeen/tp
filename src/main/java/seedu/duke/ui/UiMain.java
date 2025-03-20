package seedu.duke.ui;

import java.util.Scanner;
import java.util.ArrayList;

import seedu.duke.main.Constants;
import seedu.duke.ui.exceptions.EmptyTableException;

public class UiMain {
    private final Scanner scan;

    public UiMain() {
        this.scan = new Scanner(System.in);
    }

    public void printApplications(ArrayList<ArrayList<String>> applications) throws EmptyTableException {
        try {
            String table = UiTable.getTable(applications);
            System.out.println(table);
        } catch (EmptyTableException e) {
            System.out.println(e.getMessage());
        }

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

    public void print(String output) {
        System.out.println(output);
    }

    public void showLineBreak() {
        System.out.println(Constants.LINE_BREAK);
    }

    public void exitMessage() {
        System.out.println("Goodbye!");
        scan.close();
    }
}
