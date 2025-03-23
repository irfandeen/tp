package seedu.logjob.logic.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.logjob.logic.commands.Command;
import seedu.logjob.logic.parser.exceptions.ParseException;

public class CommandParserTestUtil {
    private static final Logger logger = Logger.getLogger(CommandParserTestUtil.class.getName());

    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput,
            Command expectedCommand) throws ParseException {
        Command command = parser.parse(userInput);
        assertEquals(expectedCommand, command);
    }

    public static void assertParseFailure(Parser<? extends Command> parser, String userinput,
            String expectedMessage) {
        try {
            parser.parse(userinput);
            logger.log(Level.INFO, "Assertion failed: ParseException not thrown for input \"" + userinput + "\"");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException e) {
            assertEquals(expectedMessage, e.getMessage());
        }

    }

}
