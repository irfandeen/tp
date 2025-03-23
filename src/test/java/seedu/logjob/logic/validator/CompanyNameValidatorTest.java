package seedu.logjob.logic.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyNameValidatorTest {
    private final CompanyNameValidator validator = new CompanyNameValidator();

    @Test
    void validate_validCompanyName_returnsTrue() {
        assertTrue(validator.validate("Google"));
        assertTrue(validator.validate("Procter & Gamble"));
        assertTrue(validator.validate("MacDonald's Singapore"));
        assertTrue(validator.validate("Baskin-Robbins"));
        assertTrue(validator.validate("7/11 Singapore"));
    }

    @Test
    void validate_invalidStatusArg_returnsFalse() {
        assertFalse(validator.validate("** Company"));
        assertFalse(validator.validate("__bank__"));
        assertFalse(validator.validate("??? company"));
        assertFalse(validator.validate("1% Corporation"));
    }

    @Test
    void validate_emptyOrNullInput_returnsFalse() throws NullPointerException {
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("  "));
        assertThrows(NullPointerException.class, () -> validator.validate(null));
    }
}
