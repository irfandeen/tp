package seedu.logjob.ui;

import org.junit.jupiter.api.Test;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    @Test
    void uiTable_nullInput_expectException() {
        ArrayList<InternshipApplication> applications = new ArrayList<>();
        assertThrows(Exception.class, () -> UiTable.getTable(applications));
    }

    @Test
    void uiTable_oneApplication_expectOneRow() throws EmptyTableException {
        ArrayList<InternshipApplication> applications = new ArrayList<>();
        InternshipApplication application = new InternshipApplication(
                "Google", "SWE", LocalDate.now(), ApplicationStatus.ACCEPTED, 1);
        applications.add(application);

        String table = UiTable.getTable(applications);
        assertTrue(table.contains("Google"));
        assertTrue(table.contains("SWE"));
        assertTrue(table.contains("ACCEPTED"));
    }

    @Test
    void printApplications_oneApplication_printsTable() throws EmptyTableException {
        // Arrange: Set up applications
        ArrayList<InternshipApplication> applications = new ArrayList<>();
        InternshipApplication application = new InternshipApplication(
                "Google", "SWE", LocalDate.now(), ApplicationStatus.ACCEPTED, 1);
        applications.add(application);
        UiMain ui = UiMain.getInstance();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        ui.printApplications(applications);

        String output = outputStream.toString();
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("SWE"));
        assertTrue(output.contains("ACCEPTED"));

        System.setOut(originalOut);
    }
}
