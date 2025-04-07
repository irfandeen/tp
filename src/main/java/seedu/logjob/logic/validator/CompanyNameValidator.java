package seedu.logjob.logic.validator;
import static java.util.Objects.requireNonNull;

/**
 * Validates company name input.
 * Ensures it follows the allowed character set and length constraints.
 * Allows alphanumeric characters and special symbols: & , - . ' / ( ) ~ ! @ # $ % ^ * _ + = ?. Limit of 50 characters.
 */
public class CompanyNameValidator implements Validator<String> {
    private static CompanyNameValidator instance;

    private static final String COMPANY_NAME_REGEX = "^[a-zA-Z0-9&,\\-.'/()~!@#$%^*_+=? ]{1,50}$";


    @Override
    public boolean validate(String companyName) {
        requireNonNull(companyName);
        String companyNameTrimmed = companyName.trim();

        return !companyNameTrimmed.isEmpty() && companyNameTrimmed.matches(COMPANY_NAME_REGEX);
    }

    public static CompanyNameValidator getInstance() {
        if (instance == null) {
            instance = new CompanyNameValidator();
        }
        return instance;
    }
}
