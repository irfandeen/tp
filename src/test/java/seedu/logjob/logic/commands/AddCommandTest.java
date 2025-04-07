package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.ui.UiMain;

import java.time.LocalDate;

public class AddCommandTest {

    @Test
    void execute_defaultStatus_applicationAdded() {
        ApplicationManagerStub dummyManager = new ApplicationManagerStub();
        String companyName = "TechCorp";
        String jobTitle = "Software Engineer";

        // Using the constructor with default status (APPLIED)
        AddCommand addCmd = new AddCommand(companyName, jobTitle, LocalDate.now(), ApplicationStatus.APPLIED);
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
        ApplicationManagerStub dummyManager = new ApplicationManagerStub();
        String companyName = "InnovateHub";
        String jobTitle = "Product Manager";
        ApplicationStatus customStatus = ApplicationStatus.INTERVIEW;

        // Using the constructor with the custom status
        AddCommand addCmd = new AddCommand(companyName, jobTitle, LocalDate.now(), ApplicationStatus.INTERVIEW);
        UiMain uiMain = UiMain.getInstance();
        addCmd.execute(dummyManager);

        // Verify the application details
        assertEquals(1, dummyManager.getApplications().size());
        InternshipApplication added = dummyManager.getApplications().get(0);
        assertEquals(companyName, added.getCompanyName());
        assertEquals(jobTitle, added.getJobTitle());
        assertEquals(customStatus, added.getStatus());
    }

    @Test
    void execute_nullCompanyName_assertionError() {
        ApplicationManagerStub dummyManager = new ApplicationManagerStub();
        // Passing a null company name to trigger an assertion failure
        String companyName = null;
        String jobTitle = "TestJob";
        LocalDate now = LocalDate.now();

        // Construct the command with a null company name
        AddCommand addCmd = new AddCommand(companyName, jobTitle, now, ApplicationStatus.APPLIED);

        // Expect an AssertionError during execution (make sure assertions are enabled via -ea)
        assertThrows(AssertionError.class, () -> addCmd.execute(dummyManager));
    }
}
