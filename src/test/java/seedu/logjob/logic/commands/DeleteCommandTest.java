package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.ui.UiMain;

import java.time.LocalDate;
import java.util.ArrayList;

public class DeleteCommandTest {

    // Dummy ApplicationManager that stores and manages applications for testing.
    private class DummyApplicationManager extends ApplicationManager {
        private final ArrayList<InternshipApplication> applications = new ArrayList<>();

        public DummyApplicationManager() {
            super(new ArrayList<InternshipApplication>());
        }

        @Override
        public void addApplication(InternshipApplication application) {
            applications.add(application);
        }

        @Override
        public void deleteApplication(int index) {
            applications.remove(index - 1);
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
    // Happy Path
    void execute_validIndex_applicationDeleted() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();

        // Add two applications to the manager.
        InternshipApplication application1 = new InternshipApplication("TechCorp", "Software Engineer",
                LocalDate.now(), ApplicationStatus.APPLIED);
        InternshipApplication application2 = new InternshipApplication("InnovateHub", "Product Manager",
                LocalDate.now(), ApplicationStatus.INTERVIEW);
        UiMain uiMain = UiMain.getInstance();
        dummyManager.addApplication(application1);
        dummyManager.addApplication(application2);

        // Delete the first application.
        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(dummyManager);

        // Verify the application at index 1 has been deleted and the remaining application is application2.
        ArrayList<InternshipApplication> remainingApps = dummyManager.getApplications();
        assertEquals(1, remainingApps.size());
        assertEquals("InnovateHub", remainingApps.get(0).getCompanyName());
        assertEquals("Product Manager", remainingApps.get(0).getJobTitle());
    }

    @Test
    // Error
    void execute_indexOutOfBounds_throwsIndexOutOfBoundsException() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();

        // Has only 1 instance at index 1
        dummyManager.addApplication(new InternshipApplication("Google", "SWE", LocalDate.now(),
                ApplicationStatus.APPLIED));

        DeleteCommand deleteCommandPositiveInvalid = new DeleteCommand(2);
        DeleteCommand deleteCommandNegativeInvalid = new DeleteCommand(-1);
        DeleteCommand deleteCommandLargePositive = new DeleteCommand(99999999);
        DeleteCommand deleteCommandLargeNegative = new DeleteCommand(-9999999);
        assertThrows(IndexOutOfBoundsException.class, () -> deleteCommandPositiveInvalid.execute(dummyManager), "Deleting out of bounds should throw an IndexOutOfBoundsException.");
        assertThrows(IndexOutOfBoundsException.class, () -> deleteCommandNegativeInvalid.execute(dummyManager), "Deleting a negative index should throw an IndexOutOfBoundsException.");
        assertThrows(IndexOutOfBoundsException.class, () -> deleteCommandLargePositive.execute(dummyManager), "Deleting a large and invalid index should throw an IndexOutOfBoundsException.");
        assertThrows(IndexOutOfBoundsException.class, () -> deleteCommandLargeNegative.execute(dummyManager),
                "Deleting a large negative index should throw an IndexOutOfBoundsException.");

    }

    @Test
    void execute_deleteFromEmptyApplicationManager_throwsIndexOutOfBoundsException() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();

        DeleteCommand deleteCommand = new DeleteCommand(0);
        DeleteCommand deleteCommandNonZero = new DeleteCommand(1);
        assertThrows(IndexOutOfBoundsException.class, () -> deleteCommand.execute(dummyManager),
                "Deleting empty application manager should throw an IndexOutOfBoundsException.");
        assertThrows(IndexOutOfBoundsException.class, () -> deleteCommandNonZero.execute(dummyManager),
                "Deleting a non-zero index on an empty application manager should throw an IndexOutOfBoundsException.");
    }
}
