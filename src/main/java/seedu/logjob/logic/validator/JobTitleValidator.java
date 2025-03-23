package seedu.logjob.logic.validator;
import static java.util.Objects.requireNonNull;
/**
 * Validates job title input.
 * Ensures it contains only allowed characters and meets length requirements.
 */
public class JobTitleValidator implements Validator<String>{
    private static JobTitleValidator instance;

    private static final String JOB_TITLE_REGEX =  "^[a-zA-Z0-9&,\\-.'/+ ]{1,100}$";

    @Override
    public boolean validate(String jobTitle) {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();

        return !trimmedJobTitle.isEmpty() && trimmedJobTitle.matches(JOB_TITLE_REGEX);
    }

    public static JobTitleValidator getInstance() {
        if (instance == null) {
            instance = new JobTitleValidator();
        }
        return instance;
    }
}
