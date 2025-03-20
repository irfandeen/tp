package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.main.Constants;
import seedu.duke.model.ApplicationManager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HelpCommandTest {

    // Dummy ApplicationManager for testing
    private class DummyApplicationManager extends ApplicationManager { }

    @Test
    void execute_printsHelpMessage() {
        // Redirect System.out to capture output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        // Execute HelpCommand
        HelpCommand helpCommand = new HelpCommand();
        DummyApplicationManager dummyManager = new DummyApplicationManager();
        helpCommand.execute(dummyManager);

        // Restore System.out
        System.setOut(originalOut);

        // Verify the output (including the new line)
        assertEquals(Constants.HELP_MESSAGE + System.lineSeparator(), output.toString());
    }
}
