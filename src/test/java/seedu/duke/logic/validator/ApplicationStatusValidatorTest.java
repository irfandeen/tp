package seedu.duke.logic.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationStatusValidatorTest {
    private final ApplicationStatusValidator validator = new ApplicationStatusValidator();

    @Test
    void validate_validStatusArg_returnsTrue() {
        assertTrue(validator.validate("Applied"));
        assertTrue(validator.validate("REJECTED"));
        assertTrue(validator.validate("REJECTED_offer"));
        assertTrue(validator.validate("0"));
        assertTrue(validator.validate("4"));
    }

    @Test
    void validate_invalidStatusArg_returnsFalse() {
        assertFalse(validator.validate("Hello There"));
        assertFalse(validator.validate("invalid"));
        assertFalse(validator.validate("-1"));
        assertFalse(validator.validate("6"));
    }

    @Test
    void validate_emptyOrNullInput_returnsFalse() throws NullPointerException {
        assertFalse(validator.validate(""));
        assertFalse(validator.validate("  "));
        assertThrows(NullPointerException.class, () -> validator.validate(null));
    }
}
