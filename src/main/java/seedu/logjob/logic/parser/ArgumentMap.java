package seedu.logjob.logic.parser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

/**
 * A map structure that stores multiple values for a single {@code Flag} key.
 */
public class ArgumentMap {
    private final HashMap<Flag, List<String>> multiMap = new HashMap<>();
    private final String preamble;

    public ArgumentMap(String preamble) {
        this.preamble = preamble;
    }

    /**
     * Adds a value to the list associated with the given flag.
     *
     * @param key the flag key
     * @param value the value to add
     */
    public void put(Flag key, String value) {
        multiMap.putIfAbsent(key, new ArrayList<>());
        multiMap.get(key).add(value);
    }

    /**
     * Returns the first value associated with the given flag, or null if none.
     *
     * @param key the flag key
     * @return the first associated value or null
     */
    public String get(Flag key) {
        if (!multiMap.containsKey(key)) {
            return null;
        }
        return multiMap.get(key).get(0);
    }

    /**
     * Returns true map contains the given Flag.
     */
    public boolean containsKey(Flag key) {
        return multiMap.containsKey(key);
    }

    /**
     * Returns true if the given flag has more than one associated value.
     *
     * @param key the flag to check
     * @return true if multiple values exist, false otherwise
     */
    private boolean containsMultipleValues(Flag key) {
        return multiMap.containsKey(key) && multiMap.get(key).size() > 1;
    }

    public String getPreamble() {
        return this.preamble;
    }

    /**
     * Returns true if map is empty.
     */
    public boolean isEmpty() {
        return multiMap.isEmpty();
    }

    /**
     * Returns number of key-value pairs in argument map
     * @return number of key value pairs
     */
    public int size() {
        return multiMap.size();
    }

    /**
     * Validates multimap to contain all input flags
     */
    public boolean missingFlags(Flag[] flags) {
        return anyFlagFails(flag -> !containsKey(flag), flags);
    }

    /**
     * Validates argument map to contain no duplicate flags
     */
    public boolean containsDuplicateFlags(Flag[] flags) {
        return anyFlagFails(flag -> containsMultipleValues(flag), flags);
    }

    private boolean anyFlagFails(Predicate<Flag> condition, Flag... flags){
        for (Flag flag : flags) {
            if (condition.test(flag)) {
                return true;
            }
        }
        return false;
    }

    public String getMissingFlags(Flag[] flags) {
        return accumulateFlags(flag -> !containsKey(flag), flags);
    }

    public String getDuplicateFlags(Flag[] flags) {
        return accumulateFlags(flag -> containsMultipleValues(flag), flags);
    }

    private String accumulateFlags(Predicate<Flag> condition, Flag[] flags) {
        StringBuilder accumulatedFlags = new StringBuilder();
        for (Flag flag : flags) {
            if (condition.test(flag)) {
                accumulatedFlags.append(flag.flag()).append(" ");
            }
        }
        return accumulatedFlags.toString();
    }

}
