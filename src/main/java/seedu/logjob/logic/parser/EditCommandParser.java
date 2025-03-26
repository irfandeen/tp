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
    private static final Flag[] EDIT_FLAGS = {
        FLAG_COMPANY_NAME, FLAG_JOB_TITLE, FLAG_APPLICATION_DATE, FLAG_APPLICATION_STATUS
    };

    @Override
    public EditCommand parse(String args) throws ParseException {
        ArgumentMap argMap = ArgumentTokenizer.tokenize(args, EDIT_FLAGS);

        // Parse index to be edited
        String editIndexString = argMap.getPreamble().trim();
        if (editIndexString.isEmpty()) {
            throw new ParseException("Edit command requires an index.");
        }
        int editIndex = ParserUtil.parseJobApplicationIndex(editIndexString);

        // Parse fields to be edited
        if (argMap.isEmpty()) {
            throw new ParseException("Edit command requires at least one argument.");
        }
        if (argMap.containsDuplicateFlags(EDIT_FLAGS)) {
            throw new ParseException("Duplicate flag(s): " + argMap.getDuplicateFlags(EDIT_FLAGS));
        }
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
