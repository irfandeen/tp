package seedu.logjob.logic.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A map structure that stores multiple values for a single {@code Flag} key.
 */
public class ArgumentMap {
    private final HashMap<Flag, List<String>> multiMap = new HashMap<>();

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
    public boolean containsMultipleValues(Flag key) {
        return multiMap.containsKey(key) && multiMap.get(key).size() > 1;
    }

    /**
     * Returns true if map is empty.
     */
    public boolean isEmpty() {
        return multiMap.isEmpty();
    }
}
