package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.FindCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

public class FindCommandParser implements Parser<FindCommand> {
    private static final String EMPTY_PREAMBLE =
            "Find command expects search term as preamble.\nExample usage: find ABC Bank";

    public FindCommand parse(String args) throws ParseException {
        ArgumentMap argumentMap = ArgumentTokenizer.tokenize(args);
        if (argumentMap.getPreamble().trim().isEmpty()) {
            throw new ParseException(EMPTY_PREAMBLE);
        }

        return new FindCommand(argumentMap.getPreamble().trim());
    }
}
