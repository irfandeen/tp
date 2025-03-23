package seedu.LogJob.logic.parser;
import seedu.LogJob.logic.commands.ListCommand;
import seedu.LogJob.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand>{
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args != null) {
            throw new ParseException("Invalid arguments: " + args);
        }
        return new ListCommand();
    }
}
