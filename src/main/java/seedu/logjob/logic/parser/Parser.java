package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.Command;

import seedu.logjob.logic.parser.exceptions.ParseException;

/**
 * Represents a generic parser that converts a user input string into a corresponding {@link Command}.
 *
 * @param <T> the type of command to be parsed.
 */
public interface Parser<T extends Command> {

    /**
     * Parses the input string and returns the corresponding command.
     *
     * @param input the user input to be parsed.
     * @return the parsed {@link Command} object.
     * @throws ParseException if the input is invalid or cannot be parsed into a valid command.
     */
    T parse(String input) throws ParseException;
}
