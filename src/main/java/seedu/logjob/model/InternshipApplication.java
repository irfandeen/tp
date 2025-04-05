package seedu.logjob.model;
import java.time.LocalDate;

public class InternshipApplication {
    private final String companyName;
    private final String jobTitle;
    private final ApplicationStatus status;
    private final LocalDate applicationDate;
    private final int id;

    public InternshipApplication (
            String companyName, String jobTitle, LocalDate applicationDate, ApplicationStatus status, int id) {

        assert companyName != null && !companyName.isEmpty() : "companyName should not be null or empty";
        assert jobTitle != null && !jobTitle.isEmpty() : "jobTitle should not be null or empty";
        assert applicationDate != null : "applicationDate should not be null";
        assert status != null : "status should not be null";
        assert id >= 1 : "id should be greater than 1";

        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.applicationDate = applicationDate;
        this.status = status;
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationDateString() {
        return applicationDate.toString();
    }

    public String getStatusToString() {
        return this.status.toString();
    }

    public ApplicationStatus getStatus() {
        return this.status;
    }

    public String toString() {
        return companyName + ", " + jobTitle + ", " + applicationDate.toString() + ", " + status.toString();
    }

    public int getId() {
        return id;
    }
}
