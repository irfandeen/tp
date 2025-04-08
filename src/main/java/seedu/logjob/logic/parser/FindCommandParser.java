package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.FindCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

/**
 * Parses input for the find command. The find command searches for applications based on the provided search term.
 */
public class FindCommandParser implements Parser<FindCommand> {
    private static final String EMPTY_PREAMBLE =
            "Find command expects search term as preamble.\nExample usage: find ABC Bank";

    /**
     * Parses the find command by extracting the search term from the input arguments.
     * If no search term is provided, a {@link ParseException} is thrown.
     *
     * @param args the input arguments, which should include the search term as a preamble.
     * @return a {@link FindCommand} object containing the parsed search term.
     * @throws ParseException if the search term is empty or invalid.
     */
    @Override

    public FindCommand parse(String args) throws ParseException {
        ArgumentMap argumentMap = ArgumentTokenizer.tokenize(args);
        if (argumentMap.getPreamble().trim().isEmpty()) {
            throw new ParseException(EMPTY_PREAMBLE);
        }

        return new FindCommand(argumentMap.getPreamble().trim());
    }
}
