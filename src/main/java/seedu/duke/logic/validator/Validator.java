package seedu.duke.logic.validator;

public interface Validator<T>{

    public boolean validate(T t);

}
