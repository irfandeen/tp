package seedu.duke.logic.parser;


import seedu.duke.logic.parser.exceptions.ParseException;
import seedu.duke.model.ApplicationStatus;
import seedu.duke.logic.validator.CompanyNameValidator;
import seedu.duke.logic.validator.JobTitleValidator;
import seedu.duke.logic.validator.ApplicationStatusValidator;


/**
 * Contains utility methods to parse strings in various XYZParser classes
 */
public class ParserUtil {
    private static final CompanyNameValidator companyNameValidator = CompanyNameValidator.getInstance();
    private static final JobTitleValidator jobTitleValidator = JobTitleValidator.getInstance();
    private static final ApplicationStatusValidator applicationStatusValidator =
            ApplicationStatusValidator.getInstance();

    public static String parseCompanyName(String companyName)
            throws ParseException {
        if (!companyNameValidator.validate(companyName)) {
            throw new ParseException("Invalid Company Name: " + companyName);
        }
        return companyName.trim();
    }

    public static String parseJobTitle(String jobTitle)
            throws ParseException {
        if (!jobTitleValidator.validate(jobTitle)) {
            throw new ParseException("Invalid Job Title: " + jobTitle);
        }
        return jobTitle.trim();
    }

    public static ApplicationStatus parseStatus(String statusString)
            throws ParseException {
        if (!applicationStatusValidator.validate(statusString)) {
            throw new ParseException("Invalid Application Status: " + statusString);
        }

        if (statusString.matches("\\d+")) {
            return ApplicationStatus.values()[Integer.parseInt(statusString)];
        } else {
            return ApplicationStatus.valueOf(statusString.toUpperCase());
        }
    }
}
