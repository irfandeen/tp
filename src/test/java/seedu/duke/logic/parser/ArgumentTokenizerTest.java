package seedu.duke.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


public class ArgumentTokenizerTest {
    private final Flag invalidFlag = new Flag("--i");
    private final Flag nameFlag = new Flag("-n");
    private final Flag WFlag = new Flag("/W");
    private final Flag zFlag = new Flag("~z");

    @Test
    public void tokenize_oneArg_success() {
        String argString = " -n Microsoft APAC ";
        HashMap<Flag, String> argMap = ArgumentTokenizer.tokenize(argString, nameFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Microsoft APAC");
    }

    @Test
    public void tokenize_multipleArgs_success() {
        String argString = " ~z first z argument     -n Bytedance Asia  /W W argument ~z second z argument  ";
        HashMap<Flag, String> argMap = ArgumentTokenizer.tokenize(argString, nameFlag, WFlag, zFlag);

        assertFalse(argMap.isEmpty());
        assertArgumentExists(argMap, nameFlag, "Bytedance Asia");
        assertArgumentExists(argMap, WFlag, "W argument");
        assertArgumentExists(argMap, zFlag, "second z argument");

        assertArgumentDoesNotExist(argMap, invalidFlag, "");
    }

    @Test
    public void tokenize_emptyArgsString_emptyHashMap() {
        String argString = " ";
        HashMap<Flag, String> argMap = ArgumentTokenizer.tokenize(argString, nameFlag);
        assertTrue(argMap.isEmpty());
        assertArgumentDoesNotExist(argMap, nameFlag, "");
    }

    private void assertArgumentExists(HashMap<Flag, String> argMap, Flag flag, String argValue) {
        assertTrue(argMap.containsKey(flag));
        assertEquals(argMap.get(flag), argValue);
    }

    private void assertArgumentDoesNotExist(HashMap<Flag, String> argMap, Flag flag, String argValue) {
        assertFalse(argMap.containsKey(flag));
        assertNull(argMap.get(flag));
    }
}
