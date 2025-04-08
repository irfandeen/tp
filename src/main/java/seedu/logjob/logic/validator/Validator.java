package seedu.logjob.logic.validator;

/**
 * A generic interface for validating input of type {@code T}.
 *
 * @param <T> the type of input to be validated.
 */
public interface Validator<T>{

    /**
     * Validates the given input.
     *
     * @param t the input to be validated.
     * @return true if the input is valid, false otherwise.
     */
    public boolean validate(T t);

}
