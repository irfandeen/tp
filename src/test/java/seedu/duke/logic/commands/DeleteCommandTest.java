package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.model.InternshipApplication;
import seedu.duke.model.ApplicationManager;
import seedu.duke.ui.UiMain;

import java.util.ArrayList;

public class DeleteCommandTest {

    // Dummy ApplicationManager that stores and manages applications for testing.
    private class DummyApplicationManager extends ApplicationManager {
        private final ArrayList<InternshipApplication> applications = new ArrayList<>();

        public DummyApplicationManager() {
            super(new ArrayList<InternshipApplication>());
        }

        @Override
        public void addApplication(InternshipApplication application, UiMain uiMain) {
            applications.add(application);
        }

        @Override
        public void deleteApplication(int index, UiMain uiMain) {
            applications.remove(index);
        }

        @Override
        public int getSize() {
            return applications.size();
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
        UiMain uiMain = new UiMain();
        dummyManager.addApplication(app1, uiMain);
        dummyManager.addApplication(app2, uiMain);
        assertEquals(2, dummyManager.getSize());

        // Delete the first application.
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.execute(dummyManager, uiMain);

        // Verify the application at index 0 has been deleted and the remaining application is app2.
        ArrayList<InternshipApplication> remainingApps = dummyManager.getApplications();
        assertEquals(1, remainingApps.size());
        assertEquals("InnovateHub", remainingApps.get(0).getCompanyName());
        assertEquals("Product Manager", remainingApps.get(0).getJobTitle());
    }
}
