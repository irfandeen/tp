package seedu.duke.logic.parser;

import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand>{
    @Override
    public HelpCommand parse(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException("Invalid arguments: " + args);
        }

        return new HelpCommand();
    }
}
