package seedu.duke.storage;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.StorageException;

import java.io.IOException;

public interface Storage {
    public String getUserPrefsFilePath();
    public void setUserPrefsFilePath(String userPrefsFilePath);
    public InternshipApplication[] readApplicationsFromFile() throws IOException, StorageException;
    public void storeApplicationsToFile() throws IOException, StorageException;
}
