package seedu.duke.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternshipApplicationTest {

    @Test
    public void testConstructorAndGetters() {
        String company = "TestCompany";
        String jobTitle = "TestJob";
        InternshipApplication app = new InternshipApplication(company, jobTitle, ApplicationStatus.APPLIED);

        // Verify that the constructor sets each field properly
        assertEquals(company, app.getCompanyName());
        assertEquals(jobTitle, app.getJobTitle());
        assertEquals(ApplicationStatus.APPLIED, app.getStatus());

        // Assuming getStatusToString() converts ApplicationStatus.APPLIED to "applied"
        assertEquals("APPLIED", app.getStatusToString());
    }
}