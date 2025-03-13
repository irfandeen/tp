package seedu.duke.model;

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
}
