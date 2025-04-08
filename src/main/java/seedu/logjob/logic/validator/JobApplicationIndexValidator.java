package seedu.logjob.logic.validator;

/**
 * Validates if a given string is a valid index for a job application.
 * The string is considered valid if it can be parsed as an integer.
 */
public class JobApplicationIndexValidator implements Validator<String> {
    private static JobApplicationIndexValidator instance;

    /**
     * Validates whether the given job application index is a valid integer.
     * The index is considered valid if it is a non-empty string that can be successfully parsed into an integer.
     *
     * @param jobApplicationIndex the string representing the job application index to be validated.
     * @return true if the string can be parsed as an integer, false otherwise.
     */
    @Override
    public boolean validate(String jobApplicationIndex) {
        String trimmedJobApplicationIndex = jobApplicationIndex.trim();

        try {
            Integer.parseInt(trimmedJobApplicationIndex);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the singleton instance of {@link JobApplicationIndexValidator}.
     * This ensures that only one instance of the validator is used across the application.
     *
     * @return the singleton instance of {@link JobApplicationIndexValidator}.
     */
    public static JobApplicationIndexValidator getInstance() {
        if (instance == null) {
            instance = new JobApplicationIndexValidator();
        }
        return instance;
    }
}
