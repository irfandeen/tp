package seedu.duke.model;

import seedu.duke.model.exceptions.InvalidApplicationStatus;

public enum ApplicationStatus {
    APPLIED,
    INTERVIEW,
    REJECTED,
    OFFERED,
    ACCEPTED,
    REJECTED_OFFER;

    public static String statusToString(ApplicationStatus status) {
        switch (status) {
        case APPLIED:
            return "Applied";
        case INTERVIEW:
            return "Interview";
        case REJECTED:
            return "Rejected";
        case OFFERED:
            return "Offered";
        case ACCEPTED:
            return "Accepted";
        case REJECTED_OFFER:
            return "Rejected Offer";
        default:
            return "Invalid Status";
        }
    }

    public static ApplicationStatus stringToStatus(String status) throws InvalidApplicationStatus {
        switch (status) {
        case "Applied":
            return ApplicationStatus.APPLIED;
        case "Interview":
            return ApplicationStatus.INTERVIEW;
        case "Rejected":
            return ApplicationStatus.REJECTED;
        case "Offered":
            return ApplicationStatus.OFFERED;
        case "Accepted":
            return ApplicationStatus.ACCEPTED;
        case "Rejected_Offer":
            return ApplicationStatus.REJECTED_OFFER;
        default:
            throw new InvalidApplicationStatus("Status string is of invalid format");
        }
    }
}
