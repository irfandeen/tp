package seedu.logjob.storage;

public class HashUtil {
    private HashUtil() {}

    public static boolean isValidHash(String sourceHash, StringBuilder stringStored) {
        int calculatedHash = stringStored.toString().hashCode();
        return sourceHash.equals(String.valueOf(calculatedHash));
    }

    public static String generateHash(StringBuilder stringStored) {
        return String.valueOf(stringStored.toString().hashCode());
    }
}
