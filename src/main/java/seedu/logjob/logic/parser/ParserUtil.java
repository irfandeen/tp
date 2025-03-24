package seedu.logjob.logic.parser;


import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.logic.validator.JobApplicationIndexValidator;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.logic.validator.CompanyNameValidator;
import seedu.logjob.logic.validator.JobTitleValidator;
import seedu.logjob.logic.validator.ApplicationStatusValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;


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

    public static LocalDate parseApplicationDate(String dateString)
            throws ParseException {
        try {
            return LocalDate.parse(dateString.trim());
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid Date Format: " + dateString);
        }
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

    /**
     * Validates argument map to contain all input flags
     * @throws ParseException Lists all missing flags
     */
    public static void containsAllFlags(ArgumentMap argMap, Flag... flags)
            throws ParseException {
        validateFlags(flag -> !argMap.containsKey(flag),
                "Missing flag(s): ", flags);
    }

    /**
     * Validates argument map to contain no duplicate flags
     * @throws ParseException Lists all duplicate flags
     */
    public static void containsNoDuplicateFlags(ArgumentMap argMap, Flag... flags)
            throws ParseException {
        validateFlags(argMap::containsMultipleValues,
                "Duplicate flag(s): ", flags);
    }

    private static void validateFlags(Predicate<Flag> condition,
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
