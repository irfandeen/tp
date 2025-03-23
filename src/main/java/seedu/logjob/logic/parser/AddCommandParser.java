package seedu.logjob.logic.parser;

import static seedu.logjob.logic.parser.CliSyntax.FLAG_COMPANY_NAME;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_JOB_TITLE;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_APPLICATION_STATUS;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.logjob.logic.commands.AddCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.model.ApplicationStatus;

/**
 * Parses input arguments and creates a new {@code AddCommand} Object
 */
public class AddCommandParser implements Parser<AddCommand>{

    /**
     * Parses input arguments string and returns an {@code AddCommand} object.
     * Requires company name and job title flags, and optionally accepts an application status flag.
     *
     * @param args full user input arguments
     * @return a new AddCommand with parsed arguments
     * @throws ParseException if required flags are missing or duplicated, or if arguments are invalid
     */
    @Override
    public AddCommand parse(String args) throws ParseException {
        HashMap<Flag, List<String>> argMap =
                ArgumentTokenizer.tokenize(args, FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_STATUS);

        containsAllFlags(argMap, FLAG_COMPANY_NAME, FLAG_JOB_TITLE);
        containsNoDuplicateFlags(argMap, FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_STATUS);

        String companyName = ParserUtil.parseCompanyName(argMap.get(FLAG_COMPANY_NAME).get(0));
        String jobTitle = ParserUtil.parseJobTitle(argMap.get(FLAG_JOB_TITLE).get(0));

        // Optional Application Status Flag
        if (!argMap.containsKey(FLAG_APPLICATION_STATUS)) {
            return new AddCommand(companyName, jobTitle);
        }
        ApplicationStatus applicationStatus = ParserUtil.parseStatus(argMap.get(FLAG_APPLICATION_STATUS).get(0));

        return new AddCommand(companyName, jobTitle, applicationStatus);

    }

    private static void containsAllFlags(HashMap<Flag, List<String>> argMap, Flag... flags)
            throws ParseException {
        validateFlags(argMap, flag -> !argMap.containsKey(flag),
                "Missing flag(s): ", flags);
    }

    private static void containsNoDuplicateFlags(HashMap<Flag, List<String>> argMap, Flag... flags)
            throws ParseException {
        validateFlags(argMap, flag -> argMap.containsKey(flag) && argMap.get(flag).size() > 1,
                "Duplicate flag(s): ", flags);
    }

    private static void validateFlags(HashMap<Flag, List<String>> argMap,
                                      Predicate<Flag> condition,
                                      String errorMessagePrefix,
                                      Flag... flags) throws ParseException {
        StringBuilder errorMessage = new StringBuilder();
        for (Flag flag : flags) {
            if (condition.test(flag)) {
                errorMessage.append(flag.toString()).append(" ");
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new ParseException(errorMessagePrefix + errorMessage);
        }
    }
}
