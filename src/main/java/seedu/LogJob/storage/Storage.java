package seedu.LogJob.storage;

import seedu.LogJob.model.InternshipApplication;
import seedu.LogJob.storage.exceptions.InvalidDelimitedStringException;
import seedu.LogJob.storage.exceptions.StorageException;

import java.io.IOException;
import java.util.ArrayList;

public interface Storage {
    /**
     * Returns file location of data file in computer
     * @return Relative file path
     */
    public String getFilePath();

    /**
     * Reads data file from memory and loads it into program.
     *
     * @return Array of InternshipApplication objects
     * @throws IOException
     * @throws StorageException
     * @throws InvalidDelimitedStringException
     */
    public ArrayList<InternshipApplication> readApplicationsFromFile()
            throws IOException, StorageException, InvalidDelimitedStringException;

    /**
     * Takes an array of InternshipApplication objects, and stores them into memory
     *
     * @param applications Array of InternshipApplication objects
     * @throws IOException is thrown when program is unable to write to file.
     * @throws StorageException is thrown when program cannot find file.
     */
    public void storeApplicationsToFile(InternshipApplication[] applications) throws StorageException;
}
