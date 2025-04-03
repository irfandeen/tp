package seedu.logjob.logic.parser;

import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.model.ApplicationStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;


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

    @Test
    void parseApplicationDate_validDate_returnsParsedDate() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        assertEquals("2025-10-01", ParserUtil.parseApplicationDate("2025-10-01").format(formatter));
        assertEquals("2025-09-15", ParserUtil.parseApplicationDate("2025-09-15").format(formatter));
        assertEquals("2025-08-30", ParserUtil.parseApplicationDate("2025-08-30").format(formatter));
    }

    @Test
    void parseApplicationDate_invalidDate_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationDate(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationDate(" "));
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationDate("2025-13-01"));
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationDate("2025-02-30"));
    }
}
