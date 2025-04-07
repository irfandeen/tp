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
     * Returns existing internship application object at index
     * @param index Index of application to be viewed
     * @return Reference to existing object
     */
    public InternshipApplication getApplication(int index) {
        return applicationList.get(index);
    }

    /**
     * Replaces the internship application at the specified index with a new application.
     *
     * @param index       The index of the application to be replaced.
     * @param application The new application to replace the existing one.
     */
    public void updateApplication(int index, InternshipApplication application) {
        applicationList.set(index, application);
    }

    /**
     * Deletes an internship application from the list.
     * @param index The index of the application to be deleted.
     */
    public void deleteApplication(int index, UiMain uiMain) {
        applicationList.remove(index);
        uiMain.printMessage("Index: " + index + " Succeeds Deletion");
    }

    /**
     * Lists all internship applications in a table format.
     */
    public void listApplication(UiMain uiMain) throws EmptyTableException {
        uiMain.printApplications(this.applicationList);
    }

    public void sortApplication(String sortBy, UiMain uiMain) throws EmptyTableException {
        assert sortBy != null : "sortBy cannot be empty";

        if (sortBy.equals("Company Name")) {
            applicationList.sort(Comparator.comparing(InternshipApplication::getCompanyName, String.CASE_INSENSITIVE_ORDER));
        } else {
            applicationList.sort(Comparator.comparing(InternshipApplication::getApplicationDate));
        }

        ApplicationManager copy = new ApplicationManager(applicationList);
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
