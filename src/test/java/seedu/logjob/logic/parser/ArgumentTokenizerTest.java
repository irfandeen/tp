package seedu.logjob.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;



public class ArgumentTokenizerTest {
    private static final Flag invalidFlag = new Flag("--i");
    private static final Flag nameFlag = new Flag("-n");
    private static final Flag jobTitleFlag = new Flag("-j");
    private static final Flag statusFlag = new Flag("-s");
    private static final Flag wFlag = new Flag("/W");
    private static final Flag zFlag = new Flag("~z");

    @Test
    public void tokenize_oneArgNoPreamble_success() {
        String argString = " -n Microsoft APAC ";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Microsoft APAC");
    }

    @Test
    public void tokenize_threeArgsNoPreamble_success() {
        // Happy path for regular add commands
        String argString = " -n JP Morgan & Chase -j Risk Analyst Intern -s 0";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag, jobTitleFlag, statusFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "JP Morgan & Chase");
        assertArgumentExists(argMap, jobTitleFlag, "Risk Analyst Intern");
        assertArgumentExists(argMap, statusFlag, "0");
    }

    @Test
    public void tokenize_duplicateArgsNoPreamble_success() {
        String argString = " ~z first z argument     -n Bytedance Asia  /W W argument ~z second z argument  ";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag, wFlag, zFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Bytedance Asia");
        assertArgumentExists(argMap, wFlag, "W argument");
        assertArgumentExists(argMap, zFlag, "first z argument");

        assertArgumentDoesNotExist(argMap, invalidFlag);
    }

    @Test
    public void tokenize_invalidFlag_success() {
        String argString = " -n Goldman Sachs -j Summer Analyst Intern";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag, jobTitleFlag, statusFlag);

        assertArgumentExists(argMap, nameFlag, "Goldman Sachs");
        assertArgumentExists(argMap, jobTitleFlag, "Summer Analyst Intern");
        // Assert that arguments do not contain the following flags
        assertArgumentDoesNotExist(argMap, statusFlag);
        assertArgumentDoesNotExist(argMap, invalidFlag);
    }

    @Test
    public void tokenize_emptyArgsString_emptyHashMap() {
        String argString = "      ";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag);
        assertTrue(argMap.getPreamble().isEmpty());
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
    }

    @Test
    public void tokenize_argStringWithNoFlags_emptyHashMapWithPreamble() {
        // Happy path for Find Commands, preamble is search term
        String argString = " Hello There. ";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag, statusFlag);
        assertEquals("Hello There.", argMap.getPreamble());
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    @Test
    public void tokenize_preambleBeforeFlags_success() {
        // Happy path for edit commands
        String argString = " 0 -n Google -j SWE";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag, jobTitleFlag);

        assertEquals("0", argMap.getPreamble());
        assertArgumentExists(argMap, nameFlag, "Google");
        assertArgumentExists(argMap, jobTitleFlag, "SWE");
    }

    @Test
    public void tokenize_preambleWithSymbols_success() {
        String argString = "#123 !invalid input -n OpenAI";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, nameFlag);

        assertEquals("#123 !invalid input", argMap.getPreamble());
        assertArgumentExists(argMap, nameFlag, "OpenAI");
    }


    @Test
    public void tokenize_emptyArgsWithFlags_success() {
        // Happy path for Sorting Commands, takes flags but no arguments
        String argString = " -n    -j -s";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, statusFlag, nameFlag, jobTitleFlag);
        assertTrue(argMap.getPreamble().isEmpty());
        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "");
        assertArgumentExists(argMap, jobTitleFlag, "");
        assertArgumentExists(argMap, statusFlag, "");

    }

    @Test
    public void tokenize_argStringWithJoinedFlags_success() {
        String argString = " -n Tiktok -j Software Engineer-s 0";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, statusFlag, nameFlag, jobTitleFlag);
        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Tiktok");
        assertArgumentExists(argMap, jobTitleFlag, "Software Engineer-s 0"); // no space prefix, -s not read
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    @Test
    public void tokenize_noInputFlags_emptyHashMapWithPreamble() {
        String argString = " -n No flags -j were input -s to tokenize this string";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString); // No input flags
        assertEquals("-n No flags -j were input -s to tokenize this string", argMap.getPreamble());
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
        assertArgumentDoesNotExist(argMap, jobTitleFlag);
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    @Test
    public void tokenize_argStringWithNoFlags_emptyHashMap() {
        String argString = "This string has no flags";
        ArgumentMap argMap = ArgumentTokenizer.tokenize(argString, statusFlag, nameFlag, jobTitleFlag);
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
        assertArgumentDoesNotExist(argMap, jobTitleFlag);
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    private void assertArgumentExists(ArgumentMap argMap, Flag flag, String argValue) {
        assertTrue(argMap.containsKey(flag));
        assertEquals(argMap.get(flag), argValue);
    }

    private void assertArgumentDoesNotExist(ArgumentMap argMap, Flag flag) {
        assertFalse(argMap.containsKey(flag));
        assertNull(argMap.get(flag));
    }
}
