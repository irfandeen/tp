package seedu.LogJob.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.LogJob.logic.commands.Command;
import seedu.LogJob.logic.commands.ExitCommand;
import seedu.LogJob.logic.commands.HelpCommand;
import seedu.LogJob.logic.commands.DeleteCommand;
import seedu.LogJob.logic.commands.AddCommand;
import seedu.LogJob.logic.commands.ListCommand;
import seedu.LogJob.logic.parser.exceptions.ParseException;

/**
 * Top level class for user input parsing.
 */
public class ApplicationParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "^(?<commandWord>\\S+)(?<arguments>.+)?$");

    public static Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException("Empty Command");
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommandParser().parse(arguments);

        default:
            throw new ParseException("Unknown command word: " + commandWord);
        }

    }


}
