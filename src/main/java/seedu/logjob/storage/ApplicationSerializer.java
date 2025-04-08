package seedu.logjob.storage;

import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;

import java.time.LocalDate;

/**
 * Utility class for serializing and deserializing {@link InternshipApplication} objects
 * to and from delimited string format for file storage.
 */
public class ApplicationSerializer {
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_FIELDS = 4;
    private static final String ERR_MSG = "Data.txt has been corrupted. LogJob will start from a blank state.";

    private ApplicationSerializer() {}

    /**
     * Converts an {@link InternshipApplication} object into a single delimited string for storage.
     *
     * @param application the internship application to serialize.
     * @return a delimited string representation of the application.
     */
    public static String applicationToDelimitedString(InternshipApplication application) {
        return application.getCompanyName() + DELIMITER + application.getJobTitle() + DELIMITER +
                application.getApplicationDateString() + DELIMITER + application.getStatusToString();
    }

    /**
     * Parses a delimited string and constructs a corresponding {@link InternshipApplication} object.
     *
     * @param delimitedString the string representation of the application, using delimiters.
     * @return the deserialized InternshipApplication object.
     * @throws InvalidDelimitedStringException if the input string is malformed or contains invalid data.
     */
    public static InternshipApplication delimitedStringToApplication(String delimitedString)
            throws InvalidDelimitedStringException {
        String[] fields = delimitedString.split(DELIMITER);
        if (fields.length != NUMBER_OF_FIELDS) {
            throw new InvalidDelimitedStringException(ERR_MSG);
        }

        ApplicationStatus status;
        LocalDate applicationDate;
        try {
            status = ApplicationStatus.stringToStatus(fields[3]);
            applicationDate = LocalDate.parse(fields[2]);
        } catch (Exception e) {
            throw new InvalidDelimitedStringException(ERR_MSG);
        }

        return new InternshipApplication(fields[0], fields[1], applicationDate, status);
    }
}
