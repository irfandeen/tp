package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.logjob.logic.commands.ListCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseFailure;

public class ListCommandParserTest {
    private static final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_returnHelpCommand() throws ParseException {
        // Argument should be null as regex matcher returns null for empty string/whitespace after command word
        assertParseSuccess(parser, "", new ListCommand());
        assertParseSuccess(parser, "    ", new ListCommand());
    }

    @Test void parse_nonEmptyArg_throwsParseException() {
        assertParseFailure(parser, null, "Invalid arguments: null");
        assertParseFailure(parser, " non-empty string  ", "Invalid arguments:  non-empty string  ");
    }

}
