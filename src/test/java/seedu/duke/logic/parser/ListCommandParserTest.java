package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.duke.logic.commands.ListCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.duke.logic.parser.CommandParserTestUtil.assertParseFailure;

public class ListCommandParserTest {
    private static final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_returnHelpCommand() throws ParseException {
        // Argument should be null as regex matcher returns null for empty string/whitespace after command word
        assertParseSuccess(parser, null, new ListCommand());
    }

    @Test void parse_nonEmptyArg_throwsParseException() {
        assertParseFailure(parser, "", "Invalid arguments: ");
        assertParseFailure(parser, " non-empty string  ", "Invalid arguments:  non-empty string  ");
    }

}
