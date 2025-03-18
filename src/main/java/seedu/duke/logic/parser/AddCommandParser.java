package seedu.duke.logic.parser;

import static seedu.duke.logic.parser.CliSyntax.FLAG_COMPANY_NAME;
import static seedu.duke.logic.parser.CliSyntax.FLAG_JOB_TITLE;
import static seedu.duke.logic.parser.CliSyntax.FLAG_APPLICATION_STATUS;

import java.util.HashMap;
import java.util.List;

import seedu.duke.logic.commands.AddCommand;
import seedu.duke.logic.parser.exceptions.ParseException;
import seedu.duke.model.ApplicationStatus;


public class AddCommandParser implements Parser<AddCommand>{

    @Override
    public AddCommand parse(String args) throws ParseException {
        HashMap<Flag, List<String>> argMap =
                ArgumentTokenizer.tokenize(args, FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_STATUS);

        containsAllFlags(argMap, FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_STATUS);
        containsNoDuplicateFlags(argMap, FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_STATUS);

        String companyName = ParserUtil.parseCompanyName(argMap.get(FLAG_COMPANY_NAME).get(0));
        String jobTitle = ParserUtil.parseJobTitle(argMap.get(FLAG_JOB_TITLE).get(0));
        ApplicationStatus applicationStatus = ParserUtil.parseStatus(argMap.get(FLAG_APPLICATION_STATUS).get(0));

        if (applicationStatus == null) {
            return new AddCommand(companyName, jobTitle);
        } else {
            return new AddCommand(companyName, jobTitle, applicationStatus);
        }
    }

    private static void containsAllFlags(HashMap<Flag, List<String>> argMap, Flag... flags)
            throws ParseException {
        for (Flag flag : flags) {
            if (!argMap.containsKey(flag)) {
                throw new ParseException("Missing flag: " + flag.flag());
            }
        }
    }

    private static void containsNoDuplicateFlags(HashMap<Flag, List<String>> argMap, Flag... flags)
            throws ParseException {
        for (Flag flag : flags) {
            if (argMap.get(flag).size() > 1) {
                throw new ParseException("Duplicate flag: " + flag.flag());
            }
        }
    }
}
