package seedu.LogJob.logic.parser;

import seedu.LogJob.logic.commands.ExitCommand;
import seedu.LogJob.logic.parser.exceptions.ParseException;

public class ExitCommandParser implements Parser<ExitCommand>{
    @Override
    public ExitCommand parse(String args) throws ParseException {
        if (args != null) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new ExitCommand();
    }
}
