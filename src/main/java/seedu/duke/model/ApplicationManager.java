package seedu.duke.model;

import seedu.duke.logic.commands.Command;
import seedu.duke.main.Constants;
import seedu.duke.ui.UiTable;

import java.util.ArrayList;

/**
 * Manages the list of internship applications.
 */
public class ApplicationManager {
    private static ArrayList<InternshipApplication> applicationList = new ArrayList<>();

    private ApplicationManager() {
    }

    /**
     * Executes the given command.
     * @param command The command to be executed.
     */
    public static void executeCommand(Command command) {
        command.execute();
    }

    /**
     * Adds a new internship application to the list.
     * @param application The application to be added.
     */
    public static void addApplication(InternshipApplication application) {
        applicationList.add(application);
    }

    /**
     * Deletes an internship application from the list.
     * @param index The index of the application to be deleted.
     */
    public static void deleteApplication(int index) {
        applicationList.remove(index);
    }

    /**
     * Lists all internship applications in a table format.
     */
    public static void listApplication() {
        ArrayList<ArrayList<String>> applicationTable = new ArrayList<>();
        applicationTable.add(Constants.TABLE_HEADER_ARRAYLIST);

        for (int i = 0; i < applicationList.size(); i++) {
            ArrayList<String> applicationRow = new ArrayList<>();
            applicationRow.add(Integer.toString(i));
            applicationRow.add(applicationList.get(i).getCompanyName());
            applicationRow.add(applicationList.get(i).getJobTitle());
            applicationRow.add(applicationList.get(i).getStatusToString());
            applicationTable.add(applicationRow);
        }
        
        UiTable.printTable(applicationTable);
    }
}
