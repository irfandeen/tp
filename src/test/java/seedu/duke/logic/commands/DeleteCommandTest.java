package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.model.InternshipApplication;
import seedu.duke.model.ApplicationManager;

import java.util.ArrayList;

public class DeleteCommandTest {

    // Dummy ApplicationManager that stores and manages applications for testing.
    private class DummyApplicationManager extends ApplicationManager {
        private final ArrayList<InternshipApplication> applications = new ArrayList<>();

        @Override
        public void addApplication(InternshipApplication application) {
            applications.add(application);
        }

        @Override
        public void deleteApplication(int index) {
            applications.remove(index);
        }

        public ArrayList<InternshipApplication> getApplications() {
            return applications;
        }
    }

    @Test
    void execute_validIndex_applicationDeleted() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();

        // Add two applications to the manager.
        InternshipApplication app1 = new InternshipApplication("TechCorp", "Software Engineer");
        InternshipApplication app2 = new InternshipApplication("InnovateHub", "Product Manager");

        dummyManager.addApplication(app1);
        dummyManager.addApplication(app2);
        assertEquals(2, dummyManager.getApplications().size());

        // Delete the first application.
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(dummyManager);

        // Verify the application at index 0 has been deleted and the remaining application is app2.
        ArrayList<InternshipApplication> remainingApps = dummyManager.getApplications();
        assertEquals(1, remainingApps.size());
        assertEquals("InnovateHub", remainingApps.get(0).getCompanyName());
        assertEquals("Product Manager", remainingApps.get(0).getJobTitle());
    }
}
