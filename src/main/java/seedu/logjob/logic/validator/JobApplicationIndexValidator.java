package seedu.logjob.logic.validator;

public class JobApplicationIndexValidator implements Validator<String> {
    private static JobApplicationIndexValidator instance;

    @Override
    public boolean validate(String jobApplicationIndex) {
        String trimmedJobApplicationIndex = jobApplicationIndex.trim();

        try {
            Integer.parseInt(trimmedJobApplicationIndex);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static JobApplicationIndexValidator getInstance() {
        if (instance == null) {
            instance = new JobApplicationIndexValidator();
        }
        return instance;
    }
}
