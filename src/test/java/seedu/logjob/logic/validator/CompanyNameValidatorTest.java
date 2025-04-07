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
        assertTrue(validator.validate("Yahoo!"));

    }

    @Test
    void validate_invalidCharactersArg_returnsFalse() {
        assertFalse(validator.validate("你好 Company"));
        assertFalse(validator.validate("€ bank"));
        assertFalse(validator.validate("© company"));
        assertFalse(validator.validate("ÆX Corporation"));
    }

    @Test
    void validate_emptyOrNullInput_returnsFalse() throws NullPointerException {
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("  "));
        assertThrows(NullPointerException.class, () -> validator.validate(null));
    }

    @Test
    void validate_overFiftyCharacters_returnsFalse() throws NullPointerException {
        assertFalse(validator.validate("123456789012345678901234567890123456789012345678901"));
    }
}
