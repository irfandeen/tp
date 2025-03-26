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
        assertParseSuccess(parser, " -i 1 -n Google",
                new EditCommand(1, "Google", null, null, null));

        // Multiple Edit Fields
        assertParseSuccess(parser, " -s 1 -j Software Engineer -n Google -i 2",
                new EditCommand(2, "Google", "Software Engineer",
                        null, ApplicationStatus.INTERVIEW));

        // All fields edited
        assertParseSuccess(parser, " -i 003 -j Software Engineer -n Google -s 3 -d 2025-03-25",
                new EditCommand(3,"Google", "Software Engineer",
                        LocalDate.of(2025,3,25),  ApplicationStatus.OFFERED));

        // Args contain special Characters
        assertParseSuccess(parser, "  -i -01 -j C++ Software Engineer -n JPMorgan Chase & Co",
                new EditCommand(-1, "JPMorgan Chase & Co",
                        "C++ Software Engineer", null, null));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        // Contains no edit index
        assertParseFailure(parser, "",
                "Missing flag(s): -i ");
        assertParseFailure(parser, "       ",
                "Missing flag(s): -i ");

        // Contains non-numeric index
        assertParseFailure(parser, " -i abc -j JobTitle ",
                "Job Application Index must be numeric: abc");
        assertParseFailure(parser, " -n Bing -i #1",
                "Job Application Index must be numeric: #1");
        assertParseFailure(parser, " -i 1 invalid -n Yahoo ",
                "Job Application Index must be numeric: 1 invalid");

        // Contains no edit fields (only has index)
        assertParseFailure(parser, " -i 0    ",
                "Edit command requires at least one argument.");
        assertParseFailure(parser, "    -i 123 ",
                "Edit command requires at least one argument.");

        // Contains duplicate fields
        assertParseFailure(parser, " -i 0 -i 1 -n Yahoo",
                "Duplicate flag(s): -i ");
        assertParseFailure(parser, " -i 0 -n CompanyName -i 1 -n CompanyName -j  JobTitle",
                "Duplicate flag(s): -i -n ");

        // Invalid argument: Company Name
        assertParseFailure(parser, " -i 0 -n    -j Software Engineer" ,
                "Invalid Company Name: ");
        assertParseFailure(parser, " -n *Invalid Company -i 1 -j Software Engineer" ,
                "Invalid Company Name: *Invalid Company");

        // Invalid argument: Job Title
        assertParseFailure(parser, " -n Google -j -i -1" ,
                "Invalid Job Title: ");
        assertParseFailure(parser, " -n Google -j  |Invalid Job Title| -i 0",
                "Invalid Job Title: |Invalid Job Title|");

        // Invalid argument: Application Status
        assertParseFailure(parser, " -s -n Bing -i 0 -j Software Engineer",
                "Invalid Application Status: ");
        assertParseFailure(parser, " -i 1 -n Bing -j Software Engineer -s I've Applied!",
                "Invalid Application Status: I've Applied!");
        assertParseFailure(parser, " -s 6 -n Bing -j Software Engineer   -i 2",
                "Invalid Application Status: 6");
        assertParseFailure(parser, " -s -1 -i 3 -n Bing -j Software Engineer",
                "Invalid Application Status: -1");
    }
}
