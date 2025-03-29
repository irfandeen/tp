package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.SortCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;

import static seedu.logjob.logic.parser.CliSyntax.FLAG_APPLICATION_DATE;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_COMPANY_NAME;

public class SortCommandParser implements Parser<SortCommand>{
    private static final Flag[] SORT_FLAGS = { FLAG_COMPANY_NAME, FLAG_APPLICATION_DATE };

    @Override
    public SortCommand parse(String args) throws ParseException {
        String sortBy = "null";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(args, SORT_FLAGS);

        if(argMap.size() != 1){
            throw new ParseException("sort command requires exactly one argument: -n or -d");
        }

        if(argMap.containsKey(FLAG_APPLICATION_DATE)){
            sortBy = "Application Date";
        } else if(argMap.containsKey(FLAG_COMPANY_NAME)){
            sortBy = "Company Name";
        }

        if(sortBy.equals("null")){
            throw new ParseException("sort command supports only -n or -d as argument");
        }

        return new SortCommand(sortBy);
    }
}
