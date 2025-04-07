package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;

import java.util.ArrayList;

public class ExitCommandTest {

    // Dummy ApplicationManager for testing
    private class DummyApplicationManager extends ApplicationManager {
        public DummyApplicationManager() {
            super(new ArrayList<InternshipApplication>());
        }
    }

    @Test
    void execute_returnsNotRunning() {
        ExitCommand exitCommand = new ExitCommand();
        DummyApplicationManager dummyManager = new DummyApplicationManager();

        // Execute the command (should do nothing but not throw an exception)
        UiMain uiMain = UiMain.getInstance();
        exitCommand.execute(dummyManager);

        // isRunning should return false
        assertFalse(exitCommand.isRunning());
    }
}
