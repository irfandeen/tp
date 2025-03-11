package seedu.duke.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Tokenizes argument strings in the form: {@code <flag>value flag<value> ...}
 * E.g. {@code <-n>Google <-j>Software Engineer <-s>Applied}
 */
public class ArgumentTokenizer {

    /**
     * Tokenizes arguments string and returns a Java Hashmap that maps each Flag to
     * their respective argument value.
     *
     * @param arguments argument strings in the form: {@code <flag>value flag<value> ...}
     * @param flags Flags that mark each argument.
     * @return HashMap object that maps flags to their arguments.
     */
    public static HashMap<Flag, String> tokenize(String arguments, Flag... flags) {
        List<FlagPosition> allFlagPositions = getFlagPositions(arguments, flags);

        return extractArguments(arguments, allFlagPositions);
    }

    private static List<FlagPosition> getFlagPositions (String arguments, Flag... flags) {
        List<FlagPosition> flagPositions = new ArrayList<>();
        for (Flag flag : flags) {
            int index = getFlagIndex(arguments, flag);
            FlagPosition position = new FlagPosition(flag, index);
            flagPositions.add(position);
        }

        return flagPositions;
    }


    private static HashMap<Flag, String> extractArguments(String arguments, List<FlagPosition> flagPositions) {
        HashMap<Flag, String> argumentMap = new HashMap<>();
        flagPositions.sort((flagPos1, flagPos2) -> flagPos1.startIndex() - flagPos2.startIndex());

        // Dummy end position to represent end of the String
        FlagPosition endPositionMarker = new FlagPosition(new Flag(""), arguments.length());
        flagPositions.add(endPositionMarker);

        for (int i = 0; i < flagPositions.size() - 1; i++) {
            FlagPosition currPosition = flagPositions.get(i);
            FlagPosition nextPosition = flagPositions.get(i + 1);

            String argumentValue = getArgumentValue(arguments, currPosition, nextPosition);
            argumentMap.put(currPosition.flag(), argumentValue);
        }

        return argumentMap;
    }


    private static int getFlagIndex(String arguments, Flag flag) {
        return arguments.indexOf(flag.toString());
    }

    private static String getArgumentValue(String arguments, FlagPosition currPosition, FlagPosition nextPosition) {
        Flag currFlag = currPosition.flag();

        int valueStartIndex = currPosition.startIndex() + currFlag.flag().length();
        int valueEndIndex = nextPosition.startIndex();

        return arguments.substring(valueStartIndex, valueEndIndex);
    }

    private record FlagPosition(Flag flag, int startIndex) {
    }
}