package seedu.LogJob.logic.parser;

import seedu.LogJob.logic.parser.exceptions.ParseException;
import seedu.LogJob.model.ApplicationStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


public class ParserUtilsTest {

    @Test
    void parseCompanyNames_validName_returnsTrimmedNames() throws ParseException {
        assertEquals("Google", ParserUtil.parseCompanyName(" Google "));
        assertEquals("Amazon Inc.", ParserUtil.parseCompanyName("Amazon Inc."));
        assertEquals("McDonald's", ParserUtil.parseCompanyName("McDonald's"));
    }

    @Test
    void parseCompanyName_invalidNames_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompanyName("")); // Empty name
        assertThrows(ParseException.class, () -> ParserUtil.parseCompanyName(" ")); // Only spaces
        assertThrows(ParseException.class, () -> ParserUtil.parseCompanyName("@InvalidName!")); // Invalid chars
    }

    @Test
    void parseJobTitle_validTitles_returnsTrimmedTitle() throws ParseException {
        assertEquals("Software Engineer", ParserUtil.parseJobTitle(" Software Engineer "));
        assertEquals("Data Scientist", ParserUtil.parseJobTitle("Data Scientist  "));
        assertEquals("C++  Developer", ParserUtil.parseJobTitle("C++  Developer"));
    }

    @Test
    void parseJobTitle_invalidTitles_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle("")); // Empty title
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle(" ")); // Only spaces
        assertThrows(ParseException.class, () -> ParserUtil.parseJobTitle("@InvalidTitle!")); // Invalid chars
    }

    @Test
    void parseStatus_validStringStatus_returnsApplicationStatus() throws ParseException {
        assertEquals(ApplicationStatus.APPLIED, ParserUtil.parseStatus("APPLIED"));
        assertEquals(ApplicationStatus.INTERVIEW, ParserUtil.parseStatus("interview")); // Case insensitive
        assertEquals(ApplicationStatus.REJECTED, ParserUtil.parseStatus("Rejected")); // Mixed case
    }

    @Test
    void parseStatus_validIntegerStatus_returnsApplicationStatus() throws ParseException {
        assertEquals(ApplicationStatus.APPLIED, ParserUtil.parseStatus("0")); // 0 -> APPLIED
        assertEquals(ApplicationStatus.INTERVIEW, ParserUtil.parseStatus("1")); // 1 -> INTERVIEW
        assertEquals(ApplicationStatus.OFFERED, ParserUtil.parseStatus("3")); // 3 -> OFFERED
    }

    @Test
    void parseStatus_invalidStatus_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus("INVALID_STATUS")); // Non-existent status
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus("10")); // Out of range
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus("-1")); // Negative index
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus("")); // Empty input
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(" ")); // Only spaces
    }
}
