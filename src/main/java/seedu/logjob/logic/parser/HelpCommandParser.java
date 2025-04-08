package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.HelpCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

/**
 * Parses input for the help command. The help command displays information about available commands.
 * No arguments are expected for this command.
 */
public class HelpCommandParser implements Parser<HelpCommand>{

    /**
     * Parses the help command.
     * If there are any arguments provided or if the arguments are invalid, a {@link ParseException} is thrown.
     * The help command does not require any arguments.
     *
     * @param args the input arguments, which must be empty or null for a valid help command.
     * @return a new {@link HelpCommand} object.
     * @throws ParseException if any arguments are provided or if the arguments are not empty.
     */
    @Override
    public HelpCommand parse(String args) throws ParseException {
        if (args == null || !args.trim().isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new HelpCommand();
    }
}
