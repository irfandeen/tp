package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.time.LocalDate;
import java.util.ArrayList;

public class SortCommandTest {

    // Dummy ApplicationManager that stores added applications.
    private class DummyApplicationManager extends ApplicationManager {
        private ArrayList<InternshipApplication> applications = new ArrayList<>();

        public DummyApplicationManager(ArrayList<InternshipApplication> applications) {
            super(applications);
            this.applications = applications;
        }

        @Override
        public void addApplication(InternshipApplication application, UiMain uiMain) {
            applications.add(application);
        }

        public ArrayList<InternshipApplication> getApplications() {
            return applications;
        }
    }

    @Test
    void execute_sortByName_listSorted() throws EmptyTableException {
        ArrayList<InternshipApplication> applications = new ArrayList<>();
        InternshipApplication applicationOne = new InternshipApplication(
                "TechCorp",
                "SWE",
                LocalDate.now(),
                ApplicationStatus.APPLIED
        );
        InternshipApplication applicationTwo = new InternshipApplication(
                "apple",
                "SWE",
                LocalDate.now(),
                ApplicationStatus.APPLIED
        );
        applications.add(applicationOne);
        applications.add(applicationTwo);

        DummyApplicationManager dummyManager = new DummyApplicationManager(applications);
        UiMain uiMain = UiMain.getInstance();

        assertEquals(2, dummyManager.getApplications().size());
        InternshipApplication firstApplication = dummyManager.getApplications().get(0);
        assertEquals("TechCorp", firstApplication.getCompanyName());

        SortCommand sortCmd = new SortCommand("Company Name");
        sortCmd.execute(dummyManager, uiMain);

        firstApplication = dummyManager.getApplications().get(0);
        assertEquals("apple", firstApplication.getCompanyName());
    }


    @Test
    void execute_emptyList_expectException() {
        DummyApplicationManager dummyManager = new DummyApplicationManager(new ArrayList<>());

        SortCommand sortCmd = new SortCommand("Company Name");
        UiMain uiMain = UiMain.getInstance();

        assertThrows(Exception.class, () -> sortCmd.execute(dummyManager, uiMain));
    }
}
