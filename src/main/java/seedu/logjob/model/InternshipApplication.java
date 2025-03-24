package seedu.logjob.model;
import java.time.LocalDate;

public class InternshipApplication {
    private final String companyName;
    private final String jobTitle;
    private final ApplicationStatus status;
    private final LocalDate applicationDate;

    public InternshipApplication (
            String companyName, String jobTitle, LocalDate applicationDate, ApplicationStatus status) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.applicationDate = applicationDate;
        this.status = status;
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
}
