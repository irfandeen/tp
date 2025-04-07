package seedu.logjob.storage;

import org.junit.jupiter.api.Test;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;
import seedu.logjob.storage.exceptions.StorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageTest {
    private static final String testFilePath = "test_data.txt";

    private void writeToFileValidJobApplications() throws IOException {
        File file = new File(testFilePath);
        FileWriter writer = new FileWriter(file);

        //TODO: The following lines must be updated whenever new fields are
        //      added to InternshipApplication class
        writer.write("Goggle;SWE;2025-01-01;applied\n");
        writer.write("John Street;HWE;2025-01-01;rejected\n");
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
    void readApplicationsFromFile_readCorrectNumberOfApplications_expectsEqualLinesAnd()
            throws IOException, InvalidDelimitedStringException, StorageException {
        writeToFileValidJobApplications();
        Storage storage = new StorageManager(testFilePath);

        File testFile = new File(testFilePath);
        ArrayList<InternshipApplication> applications;
        applications = storage.readFromFile();
        assertEquals(Files.lines(Path.of(testFilePath)).count(), applications.size(),
                "Number of applications should be the same.");
    }

    @Test
    // Error path
    void readFromFile_invalidStorageFormat_throwsInvalidDelimitedString() throws IOException {
        writeToFileInvalidJobApplications();
        Storage storage = new StorageManager(testFilePath);

        assertThrows(InvalidDelimitedStringException.class, storage::readFromFile,
                "Invalid storage format throws InvalidDelimitedStringException.");
    }


    @Test
    // happy path
    void storeToFile_allInOrder_success() throws IOException, StorageException {
        Storage storage = new StorageManager(testFilePath);

        InternshipApplication application1 = new InternshipApplication("Google", "SWE",
                LocalDate.now(), ApplicationStatus.APPLIED);


        storage.storeToFile();

    }
}
