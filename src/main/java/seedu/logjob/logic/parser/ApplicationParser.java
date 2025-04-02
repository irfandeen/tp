package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.AddCommand;
import seedu.logjob.logic.commands.DeleteCommand;
import seedu.logjob.logic.commands.EditCommand;
import seedu.logjob.logic.commands.ExitCommand;
import seedu.logjob.logic.commands.FindCommand;
import seedu.logjob.logic.commands.HelpCommand;
import seedu.logjob.logic.commands.ListCommand;
import seedu.logjob.logic.commands.SortCommand;
import seedu.logjob.logic.commands.Command;
import seedu.logjob.logic.parser.exceptions.ParseException;


/**
 * Top level class for user input parsing.
 */
public class ApplicationParser {

    public Command parseCommand(String userInput) throws ParseException {
        if (userInput == null || userInput.isEmpty()) {
            throw new ParseException("Empty Command.");
        }
        String trimmedInput = userInput.trim();
        int firstSpaceIndex = trimmedInput.indexOf(' ');

        String commandWord = trimmedInput; // Assume single word command
        String arguments = "";

        if (firstSpaceIndex != -1) { // Command is not single word
            commandWord = trimmedInput.substring(0, firstSpaceIndex);
            arguments = trimmedInput.substring(firstSpaceIndex);
        }

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommandParser().parse(arguments);

        default:
            throw new ParseException("Unknown command word: " + commandWord);
        }

    }

}
