package seedu.logjob.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.logjob.logic.commands.AddCommand;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.logic.parser.exceptions.ParseException;

import java.time.LocalDate;

import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.logjob.logic.parser.CommandParserTestUtil.assertParseFailure;

public class AddCommandParserTest {
    private static final AddCommandParser parser = new AddCommandParser();

    @Test
    void parse_validArgs_returnsAddCommand() throws ParseException {
        // Standard arg order
        assertParseSuccess(parser, " -n Google -j Software Engineer -s Applied",
                new AddCommand("Google", "Software Engineer", LocalDate.now(), ApplicationStatus.APPLIED));

        // Random arg order, enum status arg
        assertParseSuccess(parser, " -s 1 -j Software Engineer -n Google",
                new AddCommand("Google", "Software Engineer", LocalDate.now(), ApplicationStatus.INTERVIEW));

        // Optional status not provided, defaults to APPLIED
        assertParseSuccess(parser, " -j Software Engineer -n Google",
                new AddCommand("Google", "Software Engineer", LocalDate.now(), ApplicationStatus.APPLIED));

        // Args contain special Characters
        assertParseSuccess(parser, "      -s 3 -j C++ Software Engineer -n JPMorgan Chase & Co",
                new AddCommand("JPMorgan Chase & Co",
                        "C++ Software Engineer", LocalDate.now(),
                        ApplicationStatus.OFFERED));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        // missing 2 flags
        assertParseFailure(parser, "",
                "Missing or Empty flag(s): -n -j ");
        assertParseFailure(parser, " /j Software Engineer /n Yahoo",
                "Missing or Empty flag(s): -n -j ");
        assertParseFailure(parser, " -nABC -jDEF",
                "Missing or Empty flag(s): -n -j ");

        // missing 1 flag
        assertParseFailure(parser, " -n Yahoo",
                "Missing or Empty flag(s): -j ");
        assertParseFailure(parser, " -j Software Engineer",
                "Missing or Empty flag(s): -n ");
        assertParseFailure(parser, " -j Software Engineer -j AI/ML Engineer",
                "Missing or Empty flag(s): -n ");

        // contains preamble
        assertParseFailure(parser, " this job -j Software Engineer -n TechCompany",
                "Add command has no preamble: this job");
        assertParseFailure(parser, " Hello Hello -j AI/ML Engineer -j Test Engineer -n Bing",
                "Add command has no preamble: Hello Hello");

        // single duplicate flag
        assertParseFailure(parser, " -n Yahoo -n Bing -j Software Engineer",
                "Duplicate flag(s): -n ");
        assertParseFailure(parser, " -j AI/ML Engineer -j Test Engineer -n Bing",
                "Duplicate flag(s): -j ");

        // multiple duplicate flags
        assertParseFailure(parser, " -n Yahoo -n Bing -j Software Engineer -s 0 -s 1",
                "Duplicate flag(s): -n -s ");
        assertParseFailure(parser, " -n Yahoo -j Test Engineer -n Bing -j Software Engineer",
                "Duplicate flag(s): -n -j ");

        // Invalid argument: Company Name
        assertParseFailure(parser, " -n    -j Software Engineer" ,
                "Invalid Company Name: ");
        assertParseFailure(parser, " -n 谷歌 -j Software Engineer" ,
                "Invalid Company Name: 谷歌");

        // Invalid argument: Job Title
        assertParseFailure(parser, " -n Google -j" ,
                "Invalid Job Title: ");
        assertParseFailure(parser, " -n Google -j |Invalid Job Title|",
                "Invalid Job Title: |Invalid Job Title|");

        // Invalid argument: Application Status
        assertParseFailure(parser, " -s -n Bing -j Software Engineer",
                "Invalid Application Status: ");
        assertParseFailure(parser, " -n Bing -j Software Engineer -s I've Applied!",
                "Invalid Application Status: I've Applied!");
        assertParseFailure(parser, " -s 6 -n Bing -j Software Engineer ",
                "Invalid Application Status: 6");
        assertParseFailure(parser, " -s -1 -n Bing -j Software Engineer",
                "Invalid Application Status: -1");
    }
}
