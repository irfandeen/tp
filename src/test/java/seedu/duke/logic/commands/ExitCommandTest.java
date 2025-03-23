package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import seedu.duke.model.ApplicationManager;
import seedu.duke.model.InternshipApplication;
import seedu.duke.ui.UiMain;

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
        UiMain uiMain = new UiMain();
        exitCommand.execute(dummyManager, uiMain);

        // isRunning should return false
        assertFalse(exitCommand.isRunning());
    }
}
