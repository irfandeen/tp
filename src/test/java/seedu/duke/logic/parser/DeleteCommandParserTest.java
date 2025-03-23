package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.commands.DeleteCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.duke.logic.parser.CommandParserTestUtil.assertParseFailure;


class DeleteCommandParserTest {
    private static final DeleteCommandParser parser = new DeleteCommandParser();
    @Test
    // happy path
    void parse_validArgs_returnsDeleteCommand() throws ParseException {
        assertParseSuccess(parser, " 2", new DeleteCommand(2));
        assertParseSuccess(parser, " 0    ", new DeleteCommand(0));
        assertParseSuccess(parser, " -1", new DeleteCommand(-1));
        assertParseSuccess(parser, " 0003", new DeleteCommand(3));
        assertParseSuccess(parser, " 131", new DeleteCommand(131));
        assertParseSuccess(parser, " -0000234  ", new DeleteCommand(-234));

    }

    @Test
    // error path
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " this is not numeric",
                "Job Application Index must be numeric:  this is not numeric");
        assertParseFailure(parser, " 321abc",
                "Job Application Index must be numeric:  321abc");
        assertParseFailure(parser, " !@#$%",
                "Job Application Index must be numeric:  !@#$%");
        assertParseFailure(parser, "",
                "Job Application Index must be numeric: ");
        assertParseFailure(parser, " 1.5",
                "Job Application Index must be numeric:  1.5");


    }
}
