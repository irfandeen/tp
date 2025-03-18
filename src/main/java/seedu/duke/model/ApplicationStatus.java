package seedu.duke.model;

import seedu.duke.model.exceptions.InvalidApplicationStatus;

public enum ApplicationStatus {
    APPLIED,
    INTERVIEW,
    REJECTED,
    OFFERED,
    ACCEPTED,
    REJECTED_OFFER;
      
    public static ApplicationStatus stringToStatus(String status) throws InvalidApplicationStatus {
        try {
            return ApplicationStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidApplicationStatus("Status string is of invalid format");
        }
    }
}
