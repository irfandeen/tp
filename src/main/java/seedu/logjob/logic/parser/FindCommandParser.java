package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.FindCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

public class FindCommandParser implements Parser<FindCommand> {
    private static final int EXPECTED_NUMBER_OF_FLAGS = 0;
    private static final String WRONG_NUMBER_OF_FLAGS = "Wrong number of flags. Expected: " + EXPECTED_NUMBER_OF_FLAGS;
    private static final String EMPTY_PREAMBLE = "Find command expects search term as preamble.\nExample usage: find ABC Bank";

    public FindCommand parse(String args) throws ParseException {
        ArgumentMap argumentMap = ArgumentTokenizer.tokenize(args);
        if (argumentMap.size() != EXPECTED_NUMBER_OF_FLAGS) {
            throw new ParseException(WRONG_NUMBER_OF_FLAGS);
        }
        if (argumentMap.getPreamble().isEmpty()) {
            throw new ParseException(EMPTY_PREAMBLE);
        }
        
        return new FindCommand(argumentMap.getPreamble());
    }
}
