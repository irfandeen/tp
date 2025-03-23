package seedu.logjob.logic.parser;


import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.logic.validator.JobApplicationIndexValidator;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.logic.validator.CompanyNameValidator;
import seedu.logjob.logic.validator.JobTitleValidator;
import seedu.logjob.logic.validator.ApplicationStatusValidator;


/**
 * Contains utility methods to parse strings in various XYZParser classes
 */
public class ParserUtil {
    private static final CompanyNameValidator companyNameValidator = CompanyNameValidator.getInstance();
    private static final JobTitleValidator jobTitleValidator = JobTitleValidator.getInstance();
    private static final ApplicationStatusValidator applicationStatusValidator =
            ApplicationStatusValidator.getInstance();
    private static final JobApplicationIndexValidator jobApplicationIndexValidator =
            JobApplicationIndexValidator.getInstance();

    public static String parseCompanyName(String companyName)
            throws ParseException {
        if (!companyNameValidator.validate(companyName)) {
            throw new ParseException("Invalid Company Name: " + companyName);
        }
        return companyName.trim();
    }

    /**
     * Parses job application index provided by user, ensuring it is a numeric only string and its a valid index.
     * @param indexString Argument containing the index of job application
     * @return Index of job application
     * @throws ParseException
     */
    public static int parseJobApplicationIndex(String indexString) throws ParseException {
        if (!jobApplicationIndexValidator.validate(indexString)) {
            throw new ParseException("Job Application Index must be numeric: " + indexString);
        }
        return Integer.parseInt(indexString.trim());
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
