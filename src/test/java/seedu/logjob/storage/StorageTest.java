package seedu.logjob.storage;

import org.junit.jupiter.api.Test;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ReadOnlyApplication;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;
import seedu.logjob.storage.exceptions.StorageException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StorageTest {
    private static final String TEST_FILE_PATH = "test_data.txt";
    private static final String COMPARISON_FILE_PATH = "comparison_data.txt";

    private void writeToFileValidJobApplications() throws IOException {
        File file = new File(TEST_FILE_PATH);
        FileWriter writer = new FileWriter(file);

        writer.write(HashUtil.generateHash(new StringBuilder("Google;SWE;2025-01-01;APPLIED").append(
                "John Street;HWE;2025-01-01;REJECTED")) + "\n");
        writer.write("Google;SWE;2025-01-01;APPLIED\n");
        writer.write("John Street;HWE;2025-01-01;REJECTED\n");
        writer.close();
    }

    private void writeToFileInvalidJobApplications() throws IOException {
        File file = new File(TEST_FILE_PATH);
        FileWriter writer = new FileWriter(file);

        writer.write("Google;SWE;2025-01-01;APPLIED\n");
        writer.write("John Street;2025-01-01;REJECTED\n");
        writer.close();
    }

    private boolean isSameApplications(ArrayList<InternshipApplication> expected,
            ArrayList<InternshipApplication> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isFileContentSame(File fileA, File fileB) throws IOException {
        Scanner scannerA = new Scanner(fileA);
        Scanner scannerB = new Scanner(fileB);

        while (scannerA.hasNextLine() && scannerB.hasNextLine()) {
            String lineA = scannerA.nextLine();
            String lineB = scannerB.nextLine();
            if (!lineA.equals(lineB)) {
                return false;
            }

            if (scannerA.hasNextLine() && !scannerB.hasNextLine()) {
                return false;
            }

            if (!scannerA.hasNextLine() && scannerB.hasNextLine()) {
                return false;
            }
        }

        return true;
    }

    @Test
    // Happy path
    void readApplicationsFromFile_readCorrectNumberOfApplications_expectsEqual()
            throws IOException, InvalidDelimitedStringException, StorageException {
        writeToFileValidJobApplications();
        Storage storage = new StorageManager(TEST_FILE_PATH);
        ArrayList<InternshipApplication> applications;
        applications = storage.readFromFile();
        assertEquals(2, applications.size(),
                "Number of applications should be the same.");
        ArrayList<InternshipApplication> expected = new ArrayList<>();
        expected.add(new InternshipApplication("Google", "SWE",
                LocalDate.ofYearDay(2025, 1), ApplicationStatus.APPLIED));
        expected.add(new InternshipApplication("John Street", "HWE",
                LocalDate.ofYearDay(2025, 1), ApplicationStatus.REJECTED));
        assertTrue(isSameApplications(expected, applications),
                "Expected applications differ from applications read");
    }

    @Test
    // Error path
    void readFromFile_invalidStorageFormat_throwsInvalidDelimitedString() throws IOException {
        writeToFileInvalidJobApplications();
        Storage storage = new StorageManager(TEST_FILE_PATH);

        assertThrows(InvalidDelimitedStringException.class, storage::readFromFile,
                "Invalid storage format throws InvalidDelimitedStringException.");
    }


    @Test
    // happy path
    void storeToFile_allInOrder_success() throws IOException, StorageException {
        Storage storage = new StorageManager(TEST_FILE_PATH);

        // Run storeToFile with given inputs
        InternshipApplication application1 = new InternshipApplication("Google", "SWE",
                LocalDate.ofYearDay(2025, 1), ApplicationStatus.APPLIED);
        InternshipApplication application2 = new InternshipApplication("Google", "SRE",
                LocalDate.ofYearDay(2025, 1), ApplicationStatus.REJECTED);
        ArrayList<ReadOnlyApplication> applications = new ArrayList<>();
        applications.add(new ReadOnlyApplication(0, application1));
        applications.add(new ReadOnlyApplication(1, application2));
        storage.storeToFile(applications);

        // Expected output to file
        File testFile = new File(TEST_FILE_PATH);
        File comparisonFile = new File(COMPARISON_FILE_PATH);
        FileWriter writer = new FileWriter(comparisonFile);
        writer.write(HashUtil.generateHash(new StringBuilder()
                .append("Google;SWE;2025-01-01;APPLIED")
                .append("Google;SRE;2025-01-01;REJECTED")) + "\n");
        writer.write("Google;SWE;2025-01-01;APPLIED\n");
        writer.write("Google;SRE;2025-01-01;REJECTED\n");
        writer.close();

        // Files should match
        assertDoesNotThrow(() -> isFileContentSame(testFile, comparisonFile), "File comparison failed.");
        assertTrue(isFileContentSame(comparisonFile, testFile),
                "File contents different. Storage does not store as expected.");
    }

    @Test
    // boundary case
    void storeToFile_nullStorage_storesEmptyFile() throws IOException, StorageException {
        Storage storage = new StorageManager(TEST_FILE_PATH);
        File testFile = new File(TEST_FILE_PATH);

        ArrayList<ReadOnlyApplication> applications = new ArrayList<>();
        storage.storeToFile(applications);

        assertEquals(0, testFile.length(),
                "Storage should store empty file when there are no applications.");
    }
}
