package seedu.duke.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.exceptions.ParseException;

/**
 * Top level class for user input parsing.
 */
public class ApplicationParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "^(?<commandWord>\\S+)(?:\\s+(?<arguments>.+))?$");

    public static Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException("abc");
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");


        return null;
    }


}
