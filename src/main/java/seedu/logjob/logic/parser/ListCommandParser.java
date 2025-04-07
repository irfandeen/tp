package seedu.logjob.logic.parser;
import seedu.logjob.logic.commands.ListCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

/**
 * Parses input for the list command. The list command displays all applications stored.
 * No arguments are expected for this command.
 */
public class ListCommandParser implements Parser<ListCommand>{

    /**
     * Parses the list command.
     * If there are any arguments provided or if the arguments are invalid, a {@link ParseException} is thrown.
     * The list command does not require any arguments.
     *
     * @param args the input arguments, which must be empty or null for a valid list command.
     * @return a new {@link ListCommand} object.
     * @throws ParseException if any arguments are provided or if the arguments are not empty.
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args == null || !args.trim().isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }
        return new ListCommand();
    }
}
