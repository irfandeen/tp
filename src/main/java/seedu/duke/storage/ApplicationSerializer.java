package seedu.duke.storage;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;

public class ApplicationSerializer {
    private static final String SEMICOLON_SEPARATOR = ";";
    private static final int NUMBER_OF_FIELDS = 3;
    private static final String NUM_FIELDS_ERR_MSG = "Data files contains an incorrect number of fields.";

    private ApplicationSerializer() {}
    public static String applicationToDelimitedString(InternshipApplication application) {
        return "test" + SEMICOLON_SEPARATOR + "test" + SEMICOLON_SEPARATOR + "test3";
    }

    public static InternshipApplication delimitedStringToApplication(String delimitedString) throws InvalidDelimitedStringException {;
        String[] fields = delimitedString.split(SEMICOLON_SEPARATOR);
        if (fields.length != NUMBER_OF_FIELDS) {
            throw new InvalidDelimitedStringException(NUM_FIELDS_ERR_MSG);
        }

        return new InternshipApplication(fields[0], fields[1]);
    }
}
