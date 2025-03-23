package seedu.LogJob.logic.validator;

import static java.util.Objects.requireNonNull;

import seedu.LogJob.model.ApplicationStatus;
import seedu.LogJob.model.exceptions.InvalidApplicationStatus;

/**
 * Validates application status input.
 * Ensures the status is either a valid enum name or an integer representing the enum index.
 */
public class ApplicationStatusValidator implements Validator<String>{

    private static ApplicationStatusValidator instance;

    @Override
    public boolean validate(String applicationStatusString) {
        requireNonNull(applicationStatusString);
        String trimmedStatus = applicationStatusString.trim();
        if (trimmedStatus.isEmpty()) {
            return false;
        }

        return isValidString(trimmedStatus) || isValidEnum(trimmedStatus);
    }

    /**
     * Checks if input can be directly cast to ApplicationStatus
     */
    private boolean isValidString(String status) {
        try {
            ApplicationStatus.stringToStatus(status);
            return true;
        } catch (InvalidApplicationStatus e) {
            return false;
        }
    }

    /**
     * Checks if input can be cast to integer corresponding to ApplicationStatus enum
     */
    private boolean isValidEnum (String status) {
        try {
            int index = Integer.parseInt(status);
            return index >= 0 && index < ApplicationStatus.values().length;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static ApplicationStatusValidator getInstance() {
        if (instance == null) {
            instance = new ApplicationStatusValidator();
        }
        return instance;
    }

}
