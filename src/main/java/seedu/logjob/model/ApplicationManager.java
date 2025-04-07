package seedu.logjob.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the list of internship applications.
 */
public class ApplicationManager {
    private final ArrayList<InternshipApplication> applicationList;
    private ArrayList<ReadOnlyApplication> observableList;

    public ApplicationManager(ArrayList<InternshipApplication> applicationList) {
        this.applicationList = applicationList;
        this.observableList = copyApplicationToObservableList();
    }

    public void addApplication(InternshipApplication application) {
        applicationList.add(application);
        observableList = copyApplicationToObservableList();
    }

    /**
     * Replaces the internship application at the specified index with a new application.
     *
     * @param index       The index of the application to be replaced.
     * @param application The new application to replace the existing one.
     */
    public void updateApplication(int index, InternshipApplication application) {
        applicationList.set(index - 1, application);
        observableList = copyApplicationToObservableList();
    }

    /**
     * Deletes an internship application from the list.
     *
     * @param index The index of the application to be deleted.
     */
    public void deleteApplication(int index) {
        applicationList.remove(index - 1);
        observableList = copyApplicationToObservableList();
    }

    public void sortApplication(String sortBy) {
        assert sortBy != null : "sortBy cannot be empty";

        observableList = copyApplicationToObservableList();

        if (sortBy.equals("Company Name")) {
            observableList.sort(
                    Comparator.comparing(application ->
                            application.getApplication().getCompanyName(), String.CASE_INSENSITIVE_ORDER));
        } else {
            observableList.sort(Comparator.comparing(
                    application ->
                            application.getApplication().getApplicationDate()));
        }
    }

    public void findApplications(String searchTerm) {
        observableList = new ArrayList<>();

        for (int i = 0; i < applicationList.size(); i++) {
            String applicationString = applicationList.get(i).toString().toLowerCase();
            if (applicationString.contains(searchTerm.toLowerCase())) {
                ReadOnlyApplication matchedApplication = new ReadOnlyApplication(i, applicationList.get(i));
                observableList.add(matchedApplication);
            }
        }
    }

    public ArrayList<ReadOnlyApplication> getArrayList() {
        return observableList;
    }

    public int getSize() {
        return observableList.size();
    }

    /**
     * Returns existing internship application object at index
     *
     * @param index Index of application to be viewed
     * @return Reference to existing object
     */

    public InternshipApplication getApplication(int index) {
        return applicationList.get(index);
    }

    private ArrayList <ReadOnlyApplication> copyApplicationToObservableList() {
        observableList = new ArrayList<>();
        for (int i = 0; i < applicationList.size(); i++) {
            observableList.add(new ReadOnlyApplication(i, applicationList.get(i)));
        }
        return observableList;
    }

    public void listApplications(){
        observableList = copyApplicationToObservableList();
    }
}
