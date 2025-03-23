package seedu.LogJob.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.LogJob.logic.commands.Command;
import seedu.LogJob.logic.parser.exceptions.ParseException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCommandParserTest {

    @Test
    // happy path
    void parse_validArgs_returnsDeleteCommand() throws ParseException {
        String userArgs = "2";
        DeleteCommandParser parser = new DeleteCommandParser();
        Command deleteCommand = parser.parse(userArgs);
        assertNotNull(deleteCommand, "Parser should return non-null Command type object.");
    }

    @Test
    // error path
    void parse_invalidArgs_throwsParseException() {
        DeleteCommandParser parser = new DeleteCommandParser();
        String alphabeticalString = "this is not numeric";
        String alphaNumericString = "321abc";
        String symbolicString = "!@#$%";
        String emptyString = "";

        assertThrows(ParseException.class, () -> parser.parse(alphabeticalString),
                "Parser should throw exception for alphabetic string " + alphabeticalString);
        assertThrows(ParseException.class, () -> parser.parse(alphaNumericString),
                "Parser should throw exception for alphanumeric string " + alphaNumericString);
        assertThrows(ParseException.class, () -> parser.parse(symbolicString),
                "Parser should throw an exception for symbolic string " + symbolicString);
        assertThrows(ParseException.class, () -> parser.parse(emptyString),
                "Parser should throw an exception for empty string.");
    }
}
