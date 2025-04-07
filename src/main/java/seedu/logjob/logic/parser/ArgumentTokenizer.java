package seedu.logjob.logic.parser;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

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
     * @param flags     Flags that mark each argument.
     * @return          HashMap object that maps flags to their arguments.
     */
    public static ArgumentMap tokenize(String arguments, Flag... flags) {
        List<FlagPosition> allFlagPositions = getAllFlagPositions(arguments, flags);

        return extractArguments(arguments, allFlagPositions);
    }

    /**
     * Finds all positions for each flag in the given argument string.
     *
     * @param arguments argument strings in the form: {@code <flag>value flag<value> ...}
     * @param flags     Flags to be found in the String
     * @return          list of all positions of all flags in the argument string
     */
    private static List<FlagPosition> getAllFlagPositions(String arguments, Flag... flags) {
        List<Flag> allFlags = Arrays.asList(flags);
        return  allFlags.stream()
                .flatMap(flag -> getOneFlagPositions(arguments + " ", flag).stream())
                .collect(Collectors.toList());
    }

    /**
     * Helper function for {@code getAllFlagPositions}. Gets all positions for a single flag within the argument string.
     */
    private static List<FlagPosition> getOneFlagPositions (String arguments, Flag flag) {
        List<FlagPosition> flagPositions = new ArrayList<>();
        int index = getFlagIndex(arguments, flag, 0);
        while (index != -1) {
            FlagPosition position = new FlagPosition(flag, index);
            flagPositions.add(position);
            index = getFlagIndex(arguments, flag, index);
        }
        return flagPositions;
    }


    private static ArgumentMap extractArguments(String arguments, List<FlagPosition> flagPositions) {
        flagPositions.sort(Comparator.comparingInt(FlagPosition::startIndex));

        // Parse Preamble
        String preamble = arguments;
        if (!flagPositions.isEmpty()) {
            FlagPosition first = flagPositions.get(0);
            preamble = arguments.substring(0, first.startIndex());
        }
        preamble = preamble.trim();
        ArgumentMap argumentMap = new ArgumentMap(preamble);

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


    private static int getFlagIndex(String arguments, Flag flag, int startIndex) {
        // Flag should have leading and trailing whitespace to be detected.
        int index = arguments.indexOf(" " + flag.flag() + " ", startIndex);
        return (index == -1) ? -1 : index + 1;
    }

    /**
     * Returns the argument value in the argument string starting from {@code currPosition} to {@code nextPosition}
     */
    private static String getArgumentValue(String arguments, FlagPosition currPosition, FlagPosition nextPosition) {
        Flag currFlag = currPosition.flag();

        int valueStartIndex = currPosition.startIndex() + currFlag.flag().length();
        int valueEndIndex = nextPosition.startIndex();

        return arguments.substring(valueStartIndex, valueEndIndex).trim();
    }

    private record FlagPosition(Flag flag, int startIndex) {
    }
}
