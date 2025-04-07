package seedu.logjob.storage;

import org.junit.jupiter.api.Test;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationSerializerTest {
    @Test
    // happy path
    void applicationToDelimitedString_allInOrder_success() throws InvalidDelimitedStringException {
        InternshipApplication expectedApplication1 = new InternshipApplication(
                "Google", "SWE", LocalDate.ofYearDay(2025, 1),
                ApplicationStatus.APPLIED
        );
        InternshipApplication expectedApplication2 = new InternshipApplication(
                "Goooooooooogle", "S", LocalDate.ofYearDay(2025, 1),
                ApplicationStatus.APPLIED
        );

        String validDelimitedString1 = "Google;SWE;2025-01-01;APPLIED";
        String validDelimitedString2 = "Goooooooooogle;S;2025-01-01;APPLIED";

        assertEquals(validDelimitedString1, ApplicationSerializer.applicationToDelimitedString(expectedApplication1));
        assertEquals(validDelimitedString2, ApplicationSerializer.applicationToDelimitedString(expectedApplication2));
    }

    @Test
    // happy path
    void delimitedStringToApplication_allFieldsPresent_success() throws InvalidDelimitedStringException {
        String validDelimitedString = "Google;SWE;2025-01-01;APPLIED";
        String validDelimitedString2 = "Goooooooooogle;S;2025-01-01;APPLIED";

        InternshipApplication expectedApplication1 = new InternshipApplication(
                "Google", "SWE", LocalDate.ofYearDay(2025, 1),
                ApplicationStatus.APPLIED
        );
        InternshipApplication expectedApplication2 = new InternshipApplication(
                "Goooooooooogle", "S", LocalDate.ofYearDay(2025, 1),
                ApplicationStatus.APPLIED
        );

        InternshipApplication application1 = ApplicationSerializer.delimitedStringToApplication(validDelimitedString);
        InternshipApplication application2 = ApplicationSerializer.delimitedStringToApplication(validDelimitedString2);
        assertEquals(expectedApplication1, application1);
        assertEquals(expectedApplication2, application2);
    }

    @Test
    // error path
    void delimitedStringToApplication_incorrecString_throwsException() {
        String tooManySemicolons = "Google;;;;;SWE;2025-01-01;APPLIED";
        String tooFewSemicolons = "Google;SWE";
        String missingDate = "Shoppee;SWE;;APPLIED";
        String emptyFields = ";;;";

        assertThrows(InvalidDelimitedStringException.class,
                () -> ApplicationSerializer.delimitedStringToApplication(tooManySemicolons));
        assertThrows(InvalidDelimitedStringException.class,
                () -> ApplicationSerializer.delimitedStringToApplication(tooFewSemicolons));
        assertThrows(InvalidDelimitedStringException.class,
                () -> ApplicationSerializer.delimitedStringToApplication(missingDate));
        assertThrows(InvalidDelimitedStringException.class,
                () -> ApplicationSerializer.delimitedStringToApplication(emptyFields));
    }
}
