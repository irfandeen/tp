package seedu.logjob.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListCommandTest {
    @Test
    void execute_listApplication_expectCommandResult() throws EmptyTableException {
        ApplicationManager dummyManager = new ApplicationManager(new ArrayList<>());
        InternshipApplication application = new InternshipApplication(
                "golegle",
                "excelWarrior",
                LocalDate.now(),
                ApplicationStatus.APPLIED
        );
        dummyManager.addApplication(application);
        ListCommand listCommand = new ListCommand();

        // Execute the ListCommand and verify listApplication is called.
        CommandResult res = listCommand.execute(dummyManager);
        assertEquals(
                1,
                res.getObservableList().size()
        );
    }
}
