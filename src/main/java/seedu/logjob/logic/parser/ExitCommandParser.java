package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.ExitCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

public class ExitCommandParser implements Parser<ExitCommand>{
    @Override
    public ExitCommand parse(String args) throws ParseException {
        if (args == null || !args.trim().isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new ExitCommand();
    }
}
