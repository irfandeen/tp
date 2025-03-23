package seedu.logjob.logic.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobTitleValidatorTest {
    private final JobTitleValidator validator = new JobTitleValidator();

    @Test
    void validate_validCompanyName_returnsTrue() {
        assertTrue(validator.validate("Software Engineer"));
        assertTrue(validator.validate("Sr. Manager"));
        assertTrue(validator.validate("Product Manager, Growth"));
        assertTrue(validator.validate("C++ Developer"));
        assertTrue(validator.validate("CEO/Founder"));
    }

    @Test
    void validate_invalidStatusArg_returnsFalse() {
        assertFalse(validator.validate("@job title"));
        assertFalse(validator.validate("__title__"));
        assertFalse(validator.validate("??? job"));
        assertFalse(validator.validate("JOB!TITLE!"));
    }

    @Test
    void validate_emptyOrNullInput_returnsFalse() throws NullPointerException {
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("  "));
        assertThrows(NullPointerException.class, () -> validator.validate(null));
    }
}
