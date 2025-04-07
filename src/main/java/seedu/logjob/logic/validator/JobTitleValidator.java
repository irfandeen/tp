package seedu.logjob.logic.validator;
import static java.util.Objects.requireNonNull;
/**
 * Validates job title input.
 * Ensures it contains only allowed characters and meets length requirements.
 * Allows alphanumeric characters and special symbols: & , - . ' / ( ) ~ ! @ # $ % ^ * _ + = ?. Limit of 50 characters.
 */
public class JobTitleValidator implements Validator<String>{
    private static JobTitleValidator instance;

    private static final String JOB_TITLE_REGEX =  "^[a-zA-Z0-9&,\\-.'/()~!@#$%^*_+=? ]{1,50}$";

    /**
     * Validates the provided job title.
     * The job title must contain only allowed characters and must be between 1 and 50 characters in length.
     *
     * @param jobTitle the job title to be validated.
     * @return true if the job title is valid, false otherwise.
     */
    @Override
    public boolean validate(String jobTitle) {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();

        return !trimmedJobTitle.isEmpty() && trimmedJobTitle.matches(JOB_TITLE_REGEX);
    }

    /**
     * Returns the singleton instance of {@link JobTitleValidator}.
     * Ensures that only one instance of the validator is used across the application.
     *
     * @return the singleton instance of {@link JobTitleValidator}.
     */
    public static JobTitleValidator getInstance() {
        if (instance == null) {
            instance = new JobTitleValidator();
        }
        return instance;
    }
}
