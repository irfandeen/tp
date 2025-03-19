package seedu.duke.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.model.ApplicationManager;

public class ListCommandTest {

    // Dummy ApplicationManager to record if listApplication is invoked.
    private class DummyApplicationManager extends ApplicationManager {
        boolean listCalled = false;

        @Override
        public void listApplication() {
            listCalled = true;
        }

        public boolean isListCalled() {
            return listCalled;
        }
    }

    @Test
    void execute_callsListApplication() {
        DummyApplicationManager dummyManager = new DummyApplicationManager();
        ListCommand listCommand = new ListCommand();

        // Execute the ListCommand and verify listApplication is called.
        listCommand.execute(dummyManager);
        assertTrue(dummyManager.isListCalled());
    }
}