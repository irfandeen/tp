package seedu.logjob.logic.parser;

import static seedu.logjob.logic.parser.CliSyntax.FLAG_COMPANY_NAME;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_JOB_TITLE;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_APPLICATION_DATE;
import static seedu.logjob.logic.parser.CliSyntax.FLAG_APPLICATION_STATUS;


import seedu.logjob.logic.parser.exceptions.ParseException;

import seedu.logjob.logic.commands.EditCommand;

import seedu.logjob.model.ApplicationStatus;

import java.time.LocalDate;

public class EditCommandParser implements Parser<EditCommand>{

    @Override
    public EditCommand parse(String args) throws ParseException {
        // Get edit index
        String indexString = ParserUtil.getFirstWord(args);
        int editIndex = ParserUtil.parseJobApplicationIndex(indexString);

        // Parse edit arguments
        String editArgs = ParserUtil.getArguments(args);
        ArgumentMap argMap = ArgumentTokenizer.tokenize(editArgs,
                FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_DATE, FLAG_APPLICATION_STATUS);

        if (argMap.isEmpty()) {
            throw new ParseException("Edit command requires at least one argument.");
        }

        ParserUtil.containsNoDuplicateFlags(
                argMap, FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_DATE, FLAG_APPLICATION_STATUS);

        String companyName = null;
        String jobTitle = null;
        LocalDate applicationDate = null;
        ApplicationStatus applicationStatus = null;

        if (argMap.containsKey(FLAG_COMPANY_NAME)) {
            companyName = ParserUtil.parseCompanyName(argMap.get(FLAG_COMPANY_NAME));
        }
        if (argMap.containsKey(FLAG_JOB_TITLE)) {
            jobTitle = ParserUtil.parseJobTitle(argMap.get(FLAG_JOB_TITLE));
        }
        if (argMap.containsKey(FLAG_APPLICATION_DATE)) {
            applicationDate = ParserUtil.parseApplicationDate(argMap.get(FLAG_APPLICATION_DATE));
        }
        if (argMap.containsKey(FLAG_APPLICATION_STATUS)) {
            applicationStatus = ParserUtil.parseStatus(argMap.get(FLAG_APPLICATION_STATUS));
        }

        return new EditCommand(editIndex, companyName, jobTitle, applicationDate, applicationStatus);
    }
}
