package seedu.logjob.model;

import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.util.ArrayList;

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
        uiMain.addSucceedOutput(application);
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
        uiMain.deleteSucceedOutput(index);
    }

    /**
     * Lists all internship applications in a table format.
     */
    public void listApplication(UiMain uiMain) throws EmptyTableException {
        uiMain.printApplications(this.applicationList);
    }

    public ArrayList<InternshipApplication> getArrayList() {
        return applicationList;
    }

    public int getSize() {
        return applicationList.size();
    }
}
