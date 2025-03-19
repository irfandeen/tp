package seedu.duke.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.commands.AddCommand;
import seedu.duke.logic.parser.exceptions.ParseException;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    void parse_validArgs_returnsAddCommand() throws ParseException {
        String userInput = "add -n Google -j Software Engineer -s Applied";
        AddCommand command = parser.parse(userInput);

    }
}
