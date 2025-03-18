package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.duke.model.ApplicationManager;
import seedu.duke.model.ApplicationStatus;
import seedu.duke.model.InternshipApplication;
import seedu.duke.ui.UiTable;

import java.util.ArrayList;

public class JUnitTest {
    @Test
    void uiTable_nullInput_expectException() throws Exception {
        ArrayList<ArrayList<String>> input = new ArrayList<>();

        assertThrows(Exception.class, () -> UiTable.printTable(input));
    }

    @Test
    void listApplication_twoInputs_expectTable() throws Exception {
        InternshipApplication application1 = new InternshipApplication("Google", "SWE");
        InternshipApplication application2 =
                new InternshipApplication("Facebook", "Data Analyst", ApplicationStatus.INTERVIEW);
        ApplicationManager.addApplication(application1);
        ApplicationManager.addApplication(application2);
        ApplicationManager.listApplication();

    }
}
