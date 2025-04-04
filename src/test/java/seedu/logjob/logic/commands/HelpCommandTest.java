package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.logjob.ui.UiConstants;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class HelpCommandTest {

    // Dummy ApplicationManager for testing
    private class DummyApplicationManager extends ApplicationManager {
        public DummyApplicationManager() {
            super(new ArrayList<InternshipApplication>());
        }
    }

    @Test
    void execute_printsHelpMessage() {
        // Redirect System.out to capture output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        // Execute HelpCommand
        HelpCommand helpCommand = new HelpCommand();
        DummyApplicationManager dummyManager = new DummyApplicationManager();
        UiMain uiMain = UiMain.getInstance();
        helpCommand.execute(dummyManager, uiMain);

        // Restore System.out
        System.setOut(originalOut);

        // Verify the output (including the new line)
        assertEquals(UiConstants.HELP_MESSAGE + System.lineSeparator(), output.toString());
    }
}
