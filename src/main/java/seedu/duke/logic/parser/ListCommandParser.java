package seedu.duke.logic.parser;
import seedu.duke.logic.commands.ListCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand>{
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args != null) {
            throw new ParseException("Invalid arguments: " + args);
        }
        return new ListCommand();
    }
}
