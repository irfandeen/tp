package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.ui.UiMain;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditCommandTest {
    @Test
    void execute_oneFieldEdited_applicationUpdated() throws IndexOutOfBoundsException {
        // Set up application manager with one application
        ArrayList<InternshipApplication> initialList = new ArrayList<>();
        InternshipApplication original = new InternshipApplication("OldCompany", "OldRole",
                LocalDate.of(2024, 1, 1), ApplicationStatus.APPLIED);
        ApplicationManager manager = new ApplicationManager(initialList);
        manager.addApplication(original);

        EditCommand editCmd = new EditCommand(
                1,
                "NewCompany",
                null,
                null,
                null
        );

        editCmd.execute(manager);

        // Ensure one Field is edited
        InternshipApplication edited = manager.getApplication(0);
        assertEquals("NewCompany", edited.getCompanyName());
        assertEquals(original.getJobTitle(), edited.getJobTitle());
        assertEquals(original.getApplicationDate(), edited.getApplicationDate());
        assertEquals(original.getStatus(), edited.getStatus());
    }

    @Test
    void execute_allFieldEdited_applicationUpdated() throws IndexOutOfBoundsException {
        // Set up application manager with one application
        ArrayList<InternshipApplication> initialList = new ArrayList<>();
        InternshipApplication original = new InternshipApplication("OldCompany", "OldRole",
                LocalDate.of(2024, 1, 1), ApplicationStatus.APPLIED);
        initialList.add(original);
        ApplicationManager manager = new ApplicationManager(initialList);

        EditCommand editCmd = new EditCommand(
                1,
                "NewCompany",
                "NewRole",
                LocalDate.of(2025, 3, 25),
                ApplicationStatus.OFFERED
        );

        editCmd.execute(manager);

        // Ensure one Field is edited
        InternshipApplication edited = manager.getApplication(0);
        assertEquals("NewCompany", edited.getCompanyName());
        assertEquals("NewRole", edited.getJobTitle());
        assertEquals(LocalDate.of(2025, 3, 25), edited.getApplicationDate());
        assertEquals(ApplicationStatus.OFFERED, edited.getStatus());
    }

    @Test
    void execute_invalidEditIndex_throwsException() throws IndexOutOfBoundsException {
        // Set up application manager with one application
        ArrayList<InternshipApplication> initialList = new ArrayList<>();
        InternshipApplication original = new InternshipApplication("OldCompany", "OldRole",
                LocalDate.of(2024, 1, 1), ApplicationStatus.APPLIED);
        initialList.add(original);
        ApplicationManager manager = new ApplicationManager(initialList);

        EditCommand editCmd = new EditCommand(
                2, // Edit Index out of bounds
                "New Company",
                null,
                null,
                null
        );
        assertThrows(IndexOutOfBoundsException.class, () -> editCmd.execute(manager));

    }
}
