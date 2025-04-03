package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.logjob.logic.commands.FindCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseSuccess;

class FindCommandParserTest {
    private static final String EMPTY_PREAMBLE =
            "Find command expects search term as preamble.\nExample usage: find ABC Bank";

    @Test
    // happy path
    void parse_validArgs_throwsParseException() throws ParseException {
        FindCommandParser parser = new FindCommandParser();
        assertParseSuccess(parser, "keyword", new FindCommand("keyword"));
        assertParseSuccess(parser, "keyword     keyword", new FindCommand("keyword     keyword"));
        assertParseSuccess(parser, "    trailing keyword ", new FindCommand("trailing keyword"));
    }

    @Test
    // error path
    void parse_emptyArgs_throwsParseException() {
        FindCommandParser parser = new FindCommandParser();
        assertParseFailure(parser, "", EMPTY_PREAMBLE);
        assertParseFailure(parser, "    ", EMPTY_PREAMBLE);
    }
}
