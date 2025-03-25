package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.Command;
import seedu.logjob.logic.commands.ExitCommand;
import seedu.logjob.logic.commands.HelpCommand;
import seedu.logjob.logic.commands.DeleteCommand;
import seedu.logjob.logic.commands.AddCommand;
import seedu.logjob.logic.commands.ListCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;
import static seedu.logjob.logic.parser.ParserUtil.getFirstWord;
import static seedu.logjob.logic.parser.ParserUtil.getArguments;


/**
 * Top level class for user input parsing.
 */
public class ApplicationParser {

    public Command parseCommand(String userInput) throws ParseException {
        if (userInput == null || userInput.isEmpty()) {
            throw new ParseException("Empty Command.");
        }
        String commandWord = getFirstWord(userInput);
        String arguments = getArguments(userInput);

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
