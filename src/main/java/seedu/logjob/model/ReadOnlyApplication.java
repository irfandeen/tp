package seedu.logjob.model;

public record ReadOnlyApplication(int index, InternshipApplication application) {

    public ReadOnlyApplication(int index, InternshipApplication application) {
        this.index = index;
        this.application = application;
    }

    public int getIndex() {
        return index;
    }

    public InternshipApplication getApplication() {
        return application;
    }
}
