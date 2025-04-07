package seedu.logjob.storage;

import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ReadOnlyApplication;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;
import seedu.logjob.storage.exceptions.StorageException;

import java.io.IOException;
import java.util.ArrayList;

public interface Storage {
    /**
     * Reads data file from memory and loads it into program.
     *
     * @return Array of InternshipApplication objects
     * @throws IOException
     * @throws StorageException
     * @throws InvalidDelimitedStringException
     */
    public ArrayList<InternshipApplication> readFromFile()
            throws IOException, StorageException, InvalidDelimitedStringException;

    /**
     * Takes an array of InternshipApplication objects, and stores them into memory
     *
     * @param applications Array of InternshipApplication objects
     * @throws IOException is thrown when program is unable to write to file.
     * @throws StorageException is thrown when program cannot find file.
     */
    public void storeToFile(ArrayList<ReadOnlyApplication> applications) throws StorageException;
}
