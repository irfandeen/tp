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
    @Test
    void execute_sortByName_listSorted() {
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

        ApplicationManager dummyManager = new ApplicationManager(applications);

        assertEquals(2, dummyManager.getSize());
        InternshipApplication firstApplication = dummyManager.getArrayList().get(0).getApplication();
        assertEquals("TechCorp", firstApplication.getCompanyName());

        SortCommand sortCmd = new SortCommand("Company Name");
        sortCmd.execute(dummyManager);

        firstApplication = dummyManager.getArrayList().get(0).getApplication();
        assertEquals("apple", firstApplication.getCompanyName());
    }


    @Test
    void execute_emptyList_expectCommandResult() {
        ApplicationManager dummyManager = new ApplicationManager(new ArrayList<>());

        SortCommand sortCmd = new SortCommand("Company Name");
        CommandResult res = sortCmd.execute(dummyManager);
        assertEquals(
                0,
                res.getObservableList().size()
        );
    }
}
