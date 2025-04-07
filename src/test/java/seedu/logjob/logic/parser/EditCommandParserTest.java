package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.logjob.logic.commands.EditCommand;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.logic.parser.exceptions.ParseException;

import java.time.LocalDate;

import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseFailure;

public class EditCommandParserTest {
    private static final EditCommandParser parser = new EditCommandParser();

    @Test
    void parse_validArgs_returnsEditCommand() throws ParseException {
        // One Edit field
        assertParseSuccess(parser, " 1 -n Google",
                new EditCommand(1, "Google", null, null, null));

        // Multiple Edit Fields
        assertParseSuccess(parser, " 2 -j Software Engineer -s 1 -n Google ",
                new EditCommand(2, "Google", "Software Engineer",
                        null, ApplicationStatus.INTERVIEW));

        // All fields edited
        assertParseSuccess(parser, " 003 -j Software Engineer -n Google -s 3 -d 2025-03-25",
                new EditCommand(3,"Google", "Software Engineer",
                        LocalDate.of(2025,3,25),  ApplicationStatus.OFFERED));

        // Args contain special Characters
        assertParseSuccess(parser, "  -01 -j C++ Software Engineer -n JPMorgan Chase & Co",
                new EditCommand(-1, "JPMorgan Chase & Co",
                        "C++ Software Engineer", null, null));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        // Contains no edit index
        assertParseFailure(parser, "",
                "Edit command requires an index.");
        assertParseFailure(parser, " -j JobTitle",
                "Edit command requires an index.");
        assertParseFailure(parser, "       ",
                "Edit command requires an index.");

        // Contains non-numeric index
        assertParseFailure(parser, " abc -j JobTitle ",
                "Job Application Index must be numeric: abc");
        assertParseFailure(parser, " #1 -n CompanyName ",
                "Job Application Index must be numeric: #1");
        assertParseFailure(parser, " 1 invalid -n CompanyName ",
                "Job Application Index must be numeric: 1 invalid");

        // Contains no edit fields (only has index)
        assertParseFailure(parser, "    0",
                "Edit command requires at least one argument.");
        assertParseFailure(parser, "    -123 ",
                "Edit command requires at least one argument.");

        // Contains duplicate fields
        assertParseFailure(parser, " 0 -s 1 -n CompanyName -s APPLIED",
                "Duplicate flag(s): -s ");
        assertParseFailure(parser, " 1 -s 3 -n CompanyName -s INTERVIEW -n CompanyName -j  JobTitle",
                "Duplicate flag(s): -n -s ");

        // Invalid argument: Company Name
        assertParseFailure(parser, " 0 -n    -j Software Engineer" ,
                "Invalid Company Name: ");
        assertParseFailure(parser, " 1 -n 谷歌   -j Software Engineer" ,
                "Invalid Company Name: 谷歌");

        // Invalid argument: Job Title
        assertParseFailure(parser, " 3 -n Google -j" ,
                "Invalid Job Title: ");
        assertParseFailure(parser, " 4 -n Google -j  |Invalid Job Title| ",
                "Invalid Job Title: |Invalid Job Title|");

        // Invalid argument: Application Status
        assertParseFailure(parser, " 5 -s -n Bing -j Software Engineer",
                "Invalid Application Status: ");
        assertParseFailure(parser, " 6  -n Bing -j Software Engineer -s I've Applied!",
                "Invalid Application Status: I've Applied!");
        assertParseFailure(parser, " 7 -s    6 -n Bing -j Software Engineer   ",
                "Invalid Application Status: 6");
        assertParseFailure(parser, " 8   -s -1 -n Bing -j Software Engineer",
                "Invalid Application Status: -1");
    }
}
