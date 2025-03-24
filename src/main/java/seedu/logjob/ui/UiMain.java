package seedu.logjob.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void printApplications(ArrayList<InternshipApplication> applicationList) throws EmptyTableException {
        ArrayList<ArrayList<String>> applications = new ArrayList<>();
        applications.add(UiConstants.TABLE_HEADER_ARRAYLIST);

        for (int i = 0; i < applicationList.size(); i++) {

            LocalDate applicationDate = applicationList.get(i).getApplicationDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String applicationDateString = applicationDate.format(formatter);

            ArrayList<String> applicationRow = new ArrayList<>();
            applicationRow.add(Integer.toString(i));
            applicationRow.add(applicationList.get(i).getCompanyName());
            applicationRow.add(applicationList.get(i).getJobTitle());
            applicationRow.add(applicationList.get(i).getStatusToString());
            applicationRow.add(applicationDateString);
            applications.add(applicationRow);
        }

        String table = UiTable.getTable(applications);
        System.out.println(table);
    }

    public void addSucceedOutput(InternshipApplication application) {
        System.out.println(
                "Application: "
                        + application.getCompanyName()
                        + " "
                        + application.getJobTitle()
                        + " "
                        + application.getStatusToString()
                        + " Added Successfully");
    }

    public void deleteSucceedOutput(int index){
        System.out.println("Index: " + index + " Succeeds Deletion");
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
