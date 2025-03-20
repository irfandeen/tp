package seedu.duke.model;

import seedu.duke.main.Constants;
import seedu.duke.ui.UiMain;

import java.util.ArrayList;

/**
 * Manages the list of internship applications.
 */
public class ApplicationManager {
    private final ArrayList<InternshipApplication> applicationList = new ArrayList<>();

    public ApplicationManager() {
    }

    /**
     * Adds a new internship application to the list.
     * @param application The application to be added.
     */
    public void addApplication(InternshipApplication application) {
        applicationList.add(application);
    }

    /**
     * Deletes an internship application from the list.
     * @param index The index of the application to be deleted.
     */
    public void deleteApplication(int index) {
        applicationList.remove(index);
    }

    /**
     * Lists all internship applications in a table format.
     */
    public void listApplication() throws Exception {
        UiMain uiMain = new UiMain();

        ArrayList<ArrayList<String>> applications = new ArrayList<>();
        applications.add(Constants.TABLE_HEADER_ARRAYLIST);

        for (int i = 0; i < applicationList.size(); i++) {
            ArrayList<String> applicationRow = new ArrayList<>();
            applicationRow.add(Integer.toString(i));
            applicationRow.add(applicationList.get(i).getCompanyName());
            applicationRow.add(applicationList.get(i).getJobTitle());
            applicationRow.add(applicationList.get(i).getStatusToString());
            applicationRow.add("DATE_NOT_IMPLEMENTED");
            applications.add(applicationRow);
        }

        uiMain.printApplications(applications);
    }

    public ArrayList<InternshipApplication> getArrayList() {
        return applicationList;
    }
}
