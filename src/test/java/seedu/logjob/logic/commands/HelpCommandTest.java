package seedu.logjob.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.logjob.model.ApplicationManager;
import java.util.ArrayList;

public class HelpCommandTest {
    @Test
    void execute_helpCommand_emptyMessage() {
        // Execute HelpCommand
        HelpCommand helpCommand = new HelpCommand();
        ApplicationManager dummyManager = new ApplicationManager(new ArrayList<>());
        CommandResult res = helpCommand.execute(dummyManager);

        assertEquals("", res.getDisplayMessage());
        assertTrue(res.isHelp());
    }
}
