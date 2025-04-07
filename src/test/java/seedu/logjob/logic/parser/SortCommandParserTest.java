package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.logjob.logic.commands.EditCommand;
import seedu.logjob.logic.commands.SortCommand;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.logic.parser.exceptions.ParseException;

import java.time.LocalDate;

import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseFailure;

public class SortCommandParserTest {
    private static final SortCommandParser parser = new SortCommandParser();

    @Test
    void parse_validArgs_returnsSortCommand() throws ParseException {
        assertParseSuccess(parser, " -n",
                new SortCommand("Company Name"));

        assertParseSuccess(parser, " -d",
                new SortCommand("Application Date"));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " -j",
                "sort command requires exactly one argument: -n or -d");
        assertParseFailure(parser, "",
                "sort command requires exactly one argument: -n or -d");
        assertParseFailure(parser, " -n -d",
                "sort command requires exactly one argument: -n or -d");
        assertParseFailure(parser, " awdaw -n",
                "sort command requires no preamble");
        assertParseFailure(parser, " -n aija",
                "sort command requires no arguments");

    }
}

