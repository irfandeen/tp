package seedu.duke.ui;

import java.util.Scanner;

import seedu.duke.main.Constants;

public class UiMain {
    private final Scanner scan;

    public UiMain() {
        this.scan = new Scanner(System.in);
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

    public void showLineBreak() {
        System.out.println(Constants.LINE_BREAK);
    }

    public void exitMessage() {
        System.out.println("Goodbye!");
        scan.close();
    }
}
