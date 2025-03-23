package seedu.duke.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;


public class ArgumentTokenizerTest {
    private static final Flag invalidFlag = new Flag("--i");
    private static final Flag nameFlag = new Flag("-n");
    private static final Flag jobTitleFlag = new Flag("-j");
    private static final Flag statusFlag = new Flag("-s");
    private static final Flag wFlag = new Flag("/W");
    private static final Flag zFlag = new Flag("~z");

    @Test
    public void tokenize_oneArg_success() {
        String argString = " -n Microsoft APAC ";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, nameFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Microsoft APAC");
    }

    @Test
    public void tokenize_threeArgs_success() {
        String argString = " -n JP Morgan & Chase -j Risk Analyst Intern -s 0";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, nameFlag, jobTitleFlag, statusFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "JP Morgan & Chase");
        assertArgumentExists(argMap, jobTitleFlag, "Risk Analyst Intern");
        assertArgumentExists(argMap, statusFlag, "0");
    }

    @Test
    public void tokenize_duplicateArgs_success() {
        String argString = " ~z first z argument     -n Bytedance Asia  /W W argument ~z second z argument  ";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, nameFlag, wFlag, zFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Bytedance Asia");
        assertArgumentExists(argMap, wFlag, "W argument");
        assertArgumentExists(argMap, zFlag, "first z argument");

        assertArgumentDoesNotExist(argMap, invalidFlag);
    }

    @Test
    public void tokenize_invalidFlag_exit() {
        String argString = " -n Goldman Sachs -j Summer Analyst Intern";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, nameFlag, jobTitleFlag, statusFlag);

        assertArgumentExists(argMap, nameFlag, "Goldman Sachs");
        assertArgumentExists(argMap, jobTitleFlag, "Summer Analyst Intern");
        assertArgumentDoesNotExist(argMap, statusFlag);
        assertArgumentDoesNotExist(argMap, invalidFlag);
    }

    @Test
    public void tokenize_emptyArgsString_emptyHashMap() {
        String argString = " ";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, nameFlag);
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
    }

    @Test
    public void tokenize_emptyArgsWithFlags_success() {
        String argString = " -n    -j -s";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, statusFlag, nameFlag, jobTitleFlag);
        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "");
        assertArgumentExists(argMap, jobTitleFlag, "");
        assertArgumentExists(argMap, statusFlag, "");

    }

    @Test
    public void tokenize_argStringWithJoinedFlags_success() {
        String argString = " -n Tiktok -j Software Engineer-s 0";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, statusFlag, nameFlag, jobTitleFlag);
        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Tiktok");
        assertArgumentExists(argMap, jobTitleFlag, "Software Engineer-s 0");
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    @Test
    public void tokenize_noInputFlags_emptyHashMap() {
        String argString = " -n No flags -j were input -s to tokenize this string";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString);
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
        assertArgumentDoesNotExist(argMap, jobTitleFlag);
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    @Test
    public void tokenize_argStringWithNoFlags_emptyHashMap() {
        String argString = "This string has no flags";
        HashMap<Flag, List<String>> argMap = ArgumentTokenizer.tokenize(argString, statusFlag, nameFlag, jobTitleFlag);
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag);
        assertArgumentDoesNotExist(argMap, jobTitleFlag);
        assertArgumentDoesNotExist(argMap, statusFlag);
    }

    private void assertArgumentExists(HashMap<Flag, List<String>> argMap, Flag flag, String argValue) {
        assertTrue(argMap.containsKey(flag));
        assertEquals(argMap.get(flag).get(0), argValue);
    }

    private void assertArgumentDoesNotExist(HashMap<Flag, List<String>> argMap, Flag flag) {
        assertFalse(argMap.containsKey(flag));
        assertNull(argMap.get(flag));
    }
}
