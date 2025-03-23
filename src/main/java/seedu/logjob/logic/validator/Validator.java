package seedu.logjob.logic.validator;

public interface Validator<T>{

    public boolean validate(T t);

}
