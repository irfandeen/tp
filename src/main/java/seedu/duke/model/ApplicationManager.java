package seedu.duke.model;

import seedu.duke.logic.commands.Command;
import seedu.duke.main.Constants;
import seedu.duke.ui.UiTable;

import java.util.ArrayList;

public class ApplicationManager {
    private static ArrayList<InternshipApplication> applicationList = new ArrayList<>();

    private ApplicationManager() {
    }

    public static void executeCommand(Command command) {
        command.execute();
    }

    public static void addApplication(InternshipApplication application) {
        applicationList.add(application);
    }

    public static void deleteApplication(int index) {
        applicationList.remove(index);
    }

    public static void listApplication() {
        ArrayList<ArrayList<String>> applicationTable= new ArrayList<>();
        applicationTable.add(Constants.TABLE_HEADER_ARRAYLIST);

        for (int i = 0; i < applicationList.size(); i++) {
            ArrayList<String> applicationRow = new ArrayList<>();
            applicationRow.add(Integer.toString(i));
            applicationRow.add(applicationList.get(i).getCompanyName());
            applicationRow.add(applicationList.get(i).getJobTitle());
            applicationRow.add(applicationList.get(i).getStatusToString());
            applicationTable.add(applicationRow);
        }
    }
}