package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.ExitCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

/**
 * Parses input for the exit command. The exit command ends the application's execution.
 * No additional arguments are expected for this command.
 */
public class ExitCommandParser implements Parser<ExitCommand>{

    /**
     * Parses the exit command.
     * If there are any arguments provided or if the arguments are invalid, a {@link ParseException} is thrown.
     * The exit command does not require any arguments.
     *
     * @param args the input arguments, which must be empty or null for a valid exit command.
     * @return an {@link ExitCommand} object.
     * @throws ParseException if any arguments are provided or if the arguments are not empty.
     */
    @Override
    public ExitCommand parse(String args) throws ParseException {
        if (args == null || !args.trim().isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new ExitCommand();
    }
}
