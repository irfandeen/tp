package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.logjob.logic.commands.HelpCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseFailure;

public class HelpCommandParserTest {
    private static final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArg_returnHelpCommand() throws ParseException {
        // Argument should be null as regex matcher returns null for empty string/whitespace after command word
        assertParseSuccess(parser, null, new HelpCommand());
    }

    @Test void parse_nonEmptyArg_throwsParseException() {
        assertParseFailure(parser, "", "Invalid arguments: ");
        assertParseFailure(parser, " non-empty string  ",
                "Invalid arguments:  non-empty string  ");
    }

}
