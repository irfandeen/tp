package seedu.logjob.model;

import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the list of internship applications.
 */
public class ApplicationManager {
    private final ArrayList<InternshipApplication> applicationList;

    public ApplicationManager(ArrayList<InternshipApplication> applicationList) {
        this.applicationList = applicationList;
    }

    /**
     * Adds a new internship application to the list.
     * @param application The application to be added.
     */
    public void addApplication(InternshipApplication application, UiMain uiMain) {
        applicationList.add(application);
        uiMain.printMessage(
                "Application: "
                        + application.getCompanyName()
                        + " "
                        + application.getJobTitle()
                        + " "
                        + application.getStatusToString()
                        + " Added Successfully"
        );
    }

    /**
     * Returns existing internship application of the ID
     * @param id ID of application to be viewed
     * @return Reference to existing object
     */
    public InternshipApplication getApplication(int id) {
        return applicationList.get(id - 1);
    }

    /**
     * Replaces the internship application of the specified ID with a new application.
     *
     * @param id       The id of the application to be replaced.
     * @param application The new application to replace the existing one.
     */
    public void updateApplication(int id, InternshipApplication application) {
        applicationList.set(id - 1, application);
    }

    /**
     * Deletes an internship application from the list.
     * @param id The ID of the application to be deleted.
     */
    public void deleteApplication(int id, UiMain uiMain) {
        applicationList.remove(id - 1);
        uiMain.printMessage("ID: " + id + " Deleted Successfully");
    }

    /**
     * Lists all internship applications in a table format.
     */
    public void listApplication(UiMain uiMain) throws EmptyTableException {
        uiMain.printApplications(this.applicationList);
    }

    public void sortApplication(String sortBy, UiMain uiMain) throws EmptyTableException {
        ArrayList<InternshipApplication> copyList = new ArrayList<>(applicationList); // Create a copy of the list

        if (sortBy.equals("Company Name")) {
            copyList.sort(Comparator.comparing(InternshipApplication::getCompanyName, String.CASE_INSENSITIVE_ORDER));
        } else {
            copyList.sort(Comparator.comparing(InternshipApplication::getApplicationDate));
        }

        ApplicationManager copy = new ApplicationManager(copyList); // Pass the sorted copy to a new ApplicationManager
        copy.listApplication(uiMain);

        uiMain.printMessage("Applications Successfully Sorted By: " + sortBy);
    }

    public ArrayList<InternshipApplication> findApplications(String searchTerm) {
        ArrayList<InternshipApplication> applications = new ArrayList<>();
        for (InternshipApplication application : applicationList) {
            String applicationString = application.toString().toLowerCase();
            if (applicationString.contains(searchTerm.toLowerCase())) {
                applications.add(application);
            }
        }
        return applications;
    }

    public ArrayList<InternshipApplication> getArrayList() {
        return applicationList;
    }

    public int getSize() {
        return applicationList.size();
    }
}
