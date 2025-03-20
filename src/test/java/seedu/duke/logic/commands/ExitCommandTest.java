package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import seedu.duke.model.ApplicationManager;

public class ExitCommandTest {

    // Dummy ApplicationManager for testing
    private class DummyApplicationManager extends ApplicationManager { }

    @Test
    void execute_returnsNotRunning() {
        ExitCommand exitCommand = new ExitCommand();
        DummyApplicationManager dummyManager = new DummyApplicationManager();

        // Execute the command (should do nothing but not throw an exception)
        exitCommand.execute(dummyManager);

        // isRunning should return false
        assertFalse(exitCommand.isRunning());
    }
}
