package seedu.logjob.model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the list of internship applications.
 */
public class ApplicationManager {
    private final ArrayList<InternshipApplication> applicationList;
    private ArrayList<ReadOnlyApplication> observableList;

    /**
     * Constructs an ApplicationManager with the given list of internship applications.
     *
     * @param applicationList The initial list of internship applications.
     */
    public ApplicationManager(ArrayList<InternshipApplication> applicationList) {
        this.applicationList = applicationList;
        this.observableList = copyApplicationToObservableList();
    }

    /**
     * Adds a new internship application to the list.
     *
     * @param application The internship application to be added.
     */
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

    /**
     * Sorts the internship applications by the specified attribute (either "Company Name" or "Application Date").
     *
     * @param sortBy The attribute to sort by. Should be "Company Name" or "Application Date".
     */
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

    /**
     * Finds applications that match the search term.
     *
     * @param searchTerm The search term to find in application details.
     */
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

    /**
     * Returns the list of observable internship applications.
     *
     * @return An ArrayList of ReadOnlyApplication representing the current applications.
     */
    public ArrayList<ReadOnlyApplication> getArrayList() {
        return observableList;
    }

    /**
     * Returns the number of applications in the list.
     *
     * @return The size of the observable list.
     */
    public int getSize() {
        return observableList.size();
    }


    /**
     * Returns the internship application at the specified index.
     *
     * @param index The index of the application to be viewed.
     * @return The internship application at the given index.
     */
    public InternshipApplication getApplication(int index) {
        return applicationList.get(index);
    }

    /**
     * Creates a new observable list from the current internship application list.
     *
     * @return An ArrayList of ReadOnlyApplication representing the observable list.
     */
    private ArrayList <ReadOnlyApplication> copyApplicationToObservableList() {
        observableList = new ArrayList<>();
        for (int i = 0; i < applicationList.size(); i++) {
            observableList.add(new ReadOnlyApplication(i, applicationList.get(i)));
        }
        return observableList;
    }

    /**
     * Returns whether an application exists in the current model
     *
     * @param application application to be queried
     * @return boolean depending on whether application already exists
     */
    public boolean contains(InternshipApplication application) {
        return applicationList.contains(application);
    }

    /**
     * Lists all internship applications.
     * This method is a no-op that simply refreshes the observable list.
     */
    public void listApplications(){
        observableList = copyApplicationToObservableList();
    }
}
