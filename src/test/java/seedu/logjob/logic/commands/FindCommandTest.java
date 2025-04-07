package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ReadOnlyApplication;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    @Test
    void execute_existentKeywords_success() throws EmptyTableException {
        // Add sample applications
        InternshipApplication app1 = new InternshipApplication(
                "Google",
                "Software Engineer",
                LocalDate.of(2025, 1, 1),
                ApplicationStatus.APPLIED);
        InternshipApplication app2 = new InternshipApplication(
                "Shoppee",
                "Hardware Engineer",
                LocalDate.of(2025, 1, 2),
                ApplicationStatus.REJECTED
        );

        ArrayList<InternshipApplication> applications = new ArrayList<>();
        applications.add(app1);
        applications.add(app2);

        ApplicationManager applicationManager = new ApplicationManager(applications);


        FindCommand findCommandBoth = new FindCommand("Engineer");
        FindCommand findCommandSingle = new FindCommand("Shoppee");

        ArrayList<ReadOnlyApplication> bothApplications = new ArrayList<>();
        bothApplications.add(new ReadOnlyApplication(0, app1));
        bothApplications.add(new ReadOnlyApplication(1, app2));

        ArrayList<ReadOnlyApplication> singleApplications = new ArrayList<>();
        singleApplications.add(new ReadOnlyApplication(1, app2));

        CommandResult expectedResultBoth = new CommandResult(
                "2 Applications found",
                false, false,
                bothApplications
        );
        CommandResult expectedResultSingle = new CommandResult(
                "1 Applications found",
                false, false,
                singleApplications
        );

        CommandResult actualCommandResult = findCommandBoth.execute(applicationManager);
        assertEquals(actualCommandResult, expectedResultBoth);

        actualCommandResult = findCommandSingle.execute(applicationManager);
        assertEquals(actualCommandResult, expectedResultSingle);
    }

    @Test
    // negative path
    void execute_nonExistentKeywords_noResults() throws EmptyTableException {
        InternshipApplication app1 = new InternshipApplication(
                "Google",
                "Software Engineer",
                LocalDate.of(2025, 1, 1),
                ApplicationStatus.APPLIED);
        InternshipApplication app2 = new InternshipApplication(
                "Shoppee",
                "Hardware Engineer",
                LocalDate.of(2025, 1, 2),
                ApplicationStatus.REJECTED
        );

        ArrayList<InternshipApplication> applications = new ArrayList<>();
        applications.add(app1);
        applications.add(app2);

        ApplicationManager applicationManager = new ApplicationManager(applications);

        FindCommand findCommandNone = new FindCommand("Doctor");

        ArrayList<ReadOnlyApplication> noApplications = new ArrayList<>();
        CommandResult expectedResult = new CommandResult(
                "0 Applications found",
                false, false,
                noApplications
        );

        CommandResult actualResult = findCommandNone.execute(applicationManager);
        assertEquals(expectedResult, actualResult);
    }
}
