package seedu.logjob.storage;

/**
 * Utility class for generating and validating hashes for stored application data.
 * Used to ensure data integrity when reading from or writing to the storage file.
 */
public class HashUtil {
    private HashUtil() {}

    /**
     * Validates whether the given hash matches the computed hash of the provided data.
     *
     * @param sourceHash     the original hash string to validate against.
     * @param stringStored   the data content used to recompute the hash.
     * @return true if the computed hash matches the source hash, false otherwise.
     */
    public static boolean isValidHash(String sourceHash, StringBuilder stringStored) {
        int calculatedHash = stringStored.toString().hashCode();
        return sourceHash.equals(String.valueOf(calculatedHash));
    }

    /**
     * Generates a hash string based on the given data.
     *
     * @param stringStored the data for which to generate a hash.
     * @return the hash string representation of the data.
     */
    public static String generateHash(StringBuilder stringStored) {
        return String.valueOf(stringStored.toString().hashCode());
    }
}
