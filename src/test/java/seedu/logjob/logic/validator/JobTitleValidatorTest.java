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
        assertTrue(validator.validate("Software! Engineer"));
    }

    @Test
    void validate_invalidJobTitleArg_returnsFalse() {
        assertFalse(validator.validate("软件工程师"));
        assertFalse(validator.validate("Ingénieur logiciel"));
        assertFalse(validator.validate("மென்பொருள் பொறியாள"));
        assertFalse(validator.validate("ソフトウェアエンジニア"));
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
