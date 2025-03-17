package seedu.duke.storage;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.StorageException;

import java.io.IOException;

public class StorageManager implements Storage {
    private String userPrefsFilePath;

    public StorageManager(String userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    @Override
    public String getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    @Override
    public void setUserPrefsFilePath(String userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    @Override
    public InternshipApplication[] readApplicationsFromFile() throws IOException, StorageException {
        return new InternshipApplication[0];
    }

    @Override
    public void storeApplicationsToFile() throws IOException, StorageException {

    }
}
