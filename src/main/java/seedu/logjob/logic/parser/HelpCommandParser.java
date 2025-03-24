package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.HelpCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand>{
    @Override
    public HelpCommand parse(String args) throws ParseException {
        if (args == null || !args.trim().isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new HelpCommand();
    }
}
