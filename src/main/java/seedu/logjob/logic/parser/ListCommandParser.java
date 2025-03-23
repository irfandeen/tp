package seedu.logjob.logic.parser;
import seedu.logjob.logic.commands.ListCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand>{
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args != null) {
            throw new ParseException("Invalid arguments: " + args);
        }
        return new ListCommand();
    }
}
