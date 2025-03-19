package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;
import seedu.duke.storage.exceptions.StorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageTest {
    private static final String testFilePath = "test_data.txt";

    private void writeToFileValidJobApplications() throws IOException {
        File file = new File(testFilePath);
        FileWriter writer = new FileWriter(file);

        //TODO: The following lines must be updated whenever new fields are
        //      added to InternshipApplication class
        writer.write("Goggle;SWE;applied\n");
        writer.write("John Street;HWE;rejected\n");
        writer.close();
    }

    private void writeToFileInvalidJobApplications() throws IOException {
        File file = new File(testFilePath);
        FileWriter writer = new FileWriter(file);

        writer.write("Goggle;SWE;applied\n");
        writer.write("John Street;rejected\n");
        writer.close();
    }

    @Test
    // Happy path
    void readApplicationsFromFile_readCorrectNumberOfApplications_expectsEqualLinesAndApplications()
            throws IOException, InvalidDelimitedStringException, StorageException {
        writeToFileValidJobApplications();
        Storage storage = new StorageManager(testFilePath);

        File testFile = new File(testFilePath);
        InternshipApplication[] applications = storage.readApplicationsFromFile();
        assertEquals(Files.lines(Path.of(testFilePath)).count(), applications.length,
                "Number of applications should be the same.");
    }

    @Test
    // Error path
    void readApplicationsFromFile_invalidStorageFormat_throwsInvalidDelimitedString() throws IOException {
        writeToFileInvalidJobApplications();
        Storage storage = new StorageManager(testFilePath);

        assertThrows(InvalidDelimitedStringException.class, storage::readApplicationsFromFile,
                "Invalid storage format throws InvalidDelimitedStringException.");
    }
}
