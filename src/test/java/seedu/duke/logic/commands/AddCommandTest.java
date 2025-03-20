package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.model.ApplicationManager;
import seedu.duke.model.InternshipApplication;
import seedu.duke.model.ApplicationStatus;

import java.util.ArrayList;

public class AddCommandTest {

    // Dummy ApplicationManager that stores added applications.
    private class DummyApplicationManager extends ApplicationManager {
        private final ArrayList<InternshipApplication> applications = new ArrayList<>();

        @Override
        public void addApplication(InternshipApplication application) {
            applications.add(application);
        }

        public ArrayList<InternshipApplication> getApplications() {
            return applications;
        }
    }

    @Test
    void execute_defaultStatus_applicationAdded() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();
        String companyName = "TechCorp";
        String jobTitle = "Software Engineer";

        // Using the constructor with default status (APPLIED)
        AddCommand addCmd = new AddCommand(companyName, jobTitle);
        addCmd.execute(dummyManager);

        // Ensure one application is added
        assertEquals(1, dummyManager.getApplications().size());
        InternshipApplication added = dummyManager.getApplications().get(0);
        assertEquals(companyName, added.getCompanyName());
        assertEquals(jobTitle, added.getJobTitle());
        assertEquals(ApplicationStatus.APPLIED, added.getStatus());
    }

    @Test
    void execute_customStatus_applicationAdded() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();
        String companyName = "InnovateHub";
        String jobTitle = "Product Manager";
        ApplicationStatus customStatus = ApplicationStatus.INTERVIEW;

        // Using the constructor with the custom status
        AddCommand addCmd = new AddCommand(companyName, jobTitle, customStatus);
        addCmd.execute(dummyManager);

        // Verify the application details
        assertEquals(1, dummyManager.getApplications().size());
        InternshipApplication added = dummyManager.getApplications().get(0);
        assertEquals(companyName, added.getCompanyName());
        assertEquals(jobTitle, added.getJobTitle());
        assertEquals(customStatus, added.getStatus());
    }
}
