package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.logic.commands.Command;
import seedu.logjob.logic.commands.AddCommand;
import seedu.logjob.logic.commands.DeleteCommand;
import seedu.logjob.logic.commands.ExitCommand;
import seedu.logjob.logic.commands.HelpCommand;
import seedu.logjob.logic.commands.ListCommand;
import seedu.logjob.logic.commands.EditCommand;


public class ApplicationParserTest {
    private final ApplicationParser parser = new ApplicationParser();

    @Test
    public void parseCommand_validAddCommand_returnsAddCommand() throws Exception {
        Command result = parser.parseCommand("add -n Google -j SWE -s 0");
        Parser<AddCommand> expectedParser = new AddCommandParser();
        Command expectedResult = expectedParser.parse(" -n Google -j SWE -s 0");

        assertInstanceOf(AddCommand.class, result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void parseCommand_validListCommand_returnsListCommand() throws Exception {
        Command result = parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, result);
    }

    @Test
    public void parseCommand_validHelpCommand_returnsHelpCommand() throws Exception {
        Command result = parser.parseCommand("help");
        assertInstanceOf(HelpCommand.class, result);
    }

    @Test
    public void parseCommand_validDeleteCommand_returnsDeleteCommand() throws Exception {
        Command result = parser.parseCommand("delete 1");
        Parser<DeleteCommand> expectedParser = new DeleteCommandParser();
        Command expectedResult = expectedParser.parse(" 1");

        assertInstanceOf(DeleteCommand.class, result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void parseCommand_validExitCommand_returnsExitCommand() throws Exception {
        Command result = parser.parseCommand("exit");
        assertInstanceOf(ExitCommand.class, result);
    }

    @Test
    public void parseCommand_validEditCommand_returnsEditCommand() throws Exception {
        Command result = parser.parseCommand("edit -i 1 -s 1 ");
        assertInstanceOf(EditCommand.class, result);
    }

    @Test
    public void parseCommand_withLeadingAndTrailingSpaces_returnsTrimmedCommand() throws Exception {
        Command result = parser.parseCommand("   list    ");
        assertInstanceOf(ListCommand.class, result);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Exception exception = assertThrows(ParseException.class,
                () -> parser.parseCommand("hello  "));
        assertEquals("Unknown command word: hello", exception.getMessage());
    }

    @Test
    public void parseCommand_emptyInput_throwsParseException() {
        Exception exception = assertThrows(ParseException.class,
                () -> parser.parseCommand("  "));
        assertEquals("Unknown command word: ", exception.getMessage());
    }

    @Test
    public void parseCommand_nullInput_throwsParseException() {
        Exception exception = assertThrows(ParseException.class,
                () -> parser.parseCommand(null));
        assertEquals("Empty Command.", exception.getMessage());
    }

}
