package seedu.duke.logic.parser;

import seedu.duke.logic.commands.ExitCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

public class ExitCommandParser implements Parser<ExitCommand>{
    @Override
    public ExitCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new ExitCommand();
    }
}
