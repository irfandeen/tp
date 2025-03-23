package seedu.LogJob.logic.parser;

import seedu.LogJob.logic.commands.HelpCommand;
import seedu.LogJob.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand>{
    @Override
    public HelpCommand parse(String args) throws ParseException {
        if (args != null) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new HelpCommand();
    }
}
