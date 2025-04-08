package seedu.logjob.model;

import seedu.logjob.model.exceptions.InvalidApplicationStatus;

/**
 * Enum representing the status of an internship application.
 */
public enum ApplicationStatus {
    APPLIED,
    INTERVIEW,
    REJECTED,
    OFFERED,
    ACCEPTED,
    REJECTED_OFFER;

    /**
     * Converts a string to its corresponding ApplicationStatus enum value.
     *
     * @param status The status string to be converted.
     * @return The corresponding ApplicationStatus enum value.
     * @throws InvalidApplicationStatus If the string doesn't match any enum values.
     */
    public static ApplicationStatus stringToStatus(String status) throws InvalidApplicationStatus {
        try {
            return ApplicationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidApplicationStatus("Status string is of invalid format");
        }
    }
}
