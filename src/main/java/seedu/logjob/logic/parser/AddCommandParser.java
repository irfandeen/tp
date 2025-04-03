package seedu.logjob.logic.parser;

import static seedu.logjob.logic.parser.CliSyntax.FLAG_COMPANY_NAME;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_JOB_TITLE;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_APPLICATION_DATE;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_APPLICATION_STATUS;
import static seedu.logjob.model.ApplicationStatus.APPLIED;

import java.time.LocalDate;

import seedu.logjob.logic.commands.AddCommand;
import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.model.ApplicationStatus;

/**
 * Parses input arguments and creates a new {@code AddCommand} Object
 */
public class AddCommandParser implements Parser<AddCommand> {
    private static final Flag[] MANDATORY_FLAGS = {
        FLAG_COMPANY_NAME, FLAG_JOB_TITLE
    };
    private static final Flag[] ADD_FLAGS = {
        FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_DATE, FLAG_APPLICATION_STATUS
    };

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
        ArgumentMap argMap = ArgumentTokenizer.tokenize(args, ADD_FLAGS);

        if (argMap.missingFlags(MANDATORY_FLAGS)) {
            throw new ParseException("Missing flag(s): " + argMap.getMissingFlags(MANDATORY_FLAGS));
        }

        if (!argMap.getPreamble().trim().isEmpty()) {
            throw new ParseException("Add command has no preamble: " + argMap.getPreamble());
        }

        if (argMap.containsDuplicateFlags(ADD_FLAGS)) {
            throw new ParseException("Duplicate flag(s): " + argMap.getDuplicateFlags(ADD_FLAGS));
        }

        String companyName = ParserUtil.parseCompanyName(argMap.get(FLAG_COMPANY_NAME));
        String jobTitle = ParserUtil.parseJobTitle(argMap.get(FLAG_JOB_TITLE));

        // Optional Application Status Flag
        LocalDate applicationDate;
        ApplicationStatus applicationStatus;

        if (argMap.containsKey(FLAG_APPLICATION_STATUS)) {
            applicationStatus = ParserUtil.parseStatus(argMap.get(FLAG_APPLICATION_STATUS));
        } else {
            applicationStatus = APPLIED;
        }

        if (argMap.containsKey(FLAG_APPLICATION_DATE)) {
            applicationDate = ParserUtil.parseApplicationDate(argMap.get(FLAG_APPLICATION_DATE));
        } else {
            applicationDate = LocalDate.now();
        }

        return new AddCommand(companyName, jobTitle, applicationDate, applicationStatus);

    }


}
