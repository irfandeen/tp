package seedu.logjob.storage;

import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.exceptions.InvalidApplicationStatus;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;

public class ApplicationSerializer {
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_FIELDS = 3;
    private static final String NUM_FIELDS_ERR_MSG = "Data files contains an incorrect number of fields.";

    private ApplicationSerializer() {
    }

    public static String applicationToDelimitedString(InternshipApplication application) {
        return application.getCompanyName() + DELIMITER + application.getJobTitle() + DELIMITER +
                application.getStatusToString();
    }

    public static InternshipApplication delimitedStringToApplication(String delimitedString)
            throws InvalidDelimitedStringException {
        String[] fields = delimitedString.split(DELIMITER);
        if (fields.length != NUMBER_OF_FIELDS) {
            throw new InvalidDelimitedStringException(NUM_FIELDS_ERR_MSG);
        }

        ApplicationStatus status;
        try {
            status = ApplicationStatus.stringToStatus(fields[2]);
        } catch (InvalidApplicationStatus e) {
            throw new InvalidDelimitedStringException("Invalid application status: " + fields[2]);
        }

        return new InternshipApplication(fields[0], fields[1], status);
    }
}
