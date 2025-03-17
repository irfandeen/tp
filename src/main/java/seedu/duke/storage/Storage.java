package seedu.duke.storage;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;
import seedu.duke.storage.exceptions.StorageException;

import java.io.IOException;

public interface Storage {
    /**
     * Returns file location of data file in computer
     * @return Relative file path
     */
    public String getUserPrefsFilePath();

    /**
     * Set the preferred location to store application data.
     * If left undeclared, will be intialized to default location.
     *
     * @param userPrefsFilePath Relative file path of data file
     */
    public void setUserPrefsFilePath(String userPrefsFilePath);

    public InternshipApplication[] readApplicationsFromFile() throws IOException, StorageException, InvalidDelimitedStringException;
    public void storeApplicationsToFile(InternshipApplication[] applications) throws IOException, StorageException;
}
