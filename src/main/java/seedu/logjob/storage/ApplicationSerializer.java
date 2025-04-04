package seedu.logjob.storage;

import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.exceptions.InvalidApplicationStatus;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ApplicationSerializer {
    private static final String DELIMITER = ";";
    private static final int NUMBER_OF_FIELDS = 5;
    private static final String NUM_FIELDS_ERR_MSG = "Data files contains an incorrect number of fields.";

    private ApplicationSerializer() {}

    public static String applicationToDelimitedString(InternshipApplication application) {
        return application.getCompanyName() + DELIMITER + application.getJobTitle() + DELIMITER +
                application.getApplicationDateString() + DELIMITER + application.getStatusToString() +
                DELIMITER + application.getId();
    }

    public static InternshipApplication delimitedStringToApplication(String delimitedString)
            throws InvalidDelimitedStringException {
        String[] fields = delimitedString.split(DELIMITER);
        if (fields.length != NUMBER_OF_FIELDS) {
            throw new InvalidDelimitedStringException(NUM_FIELDS_ERR_MSG);
        }

        ApplicationStatus status;
        try {
            status = ApplicationStatus.stringToStatus(fields[3]);
        } catch (InvalidApplicationStatus e) {
            throw new InvalidDelimitedStringException("Invalid application status: " + fields[3]);
        }

        LocalDate applicationDate;
        try {
            applicationDate = LocalDate.parse(fields[2]);
        } catch (DateTimeParseException e) {
            throw new InvalidDelimitedStringException("Invalid application date: " + fields[2]);
        }

        int id = -1;
        try{
            id = Integer.parseInt(fields[4]);
        } catch (NumberFormatException e) {
            throw new InvalidDelimitedStringException("Invalid application id: " + fields[4]);
        }

        return new InternshipApplication(fields[0], fields[1], applicationDate, status, id);
    }
}
