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
