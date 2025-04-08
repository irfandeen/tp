package seedu.logjob.storage;

import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ReadOnlyApplication;
import seedu.logjob.storage.exceptions.InvalidDelimitedStringException;
import seedu.logjob.storage.exceptions.StorageException;
import seedu.logjob.util.RootLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * StorageManager implements the {@link Storage} interface.
 * StorageManager manages storage operations such as reading from and writing to the application data file.
 * Additionally, it mandates checksum validation to ensure file integrity in reading and writing operations.
 */
public class StorageManager implements Storage {
    private static final String DEFAULT_FILE_PATH = "data.txt";
    private static final String FILE_NOT_FOUND_FAILURE = "File does not exist && StorageException not thrown";
    private static final String CHECKSUM_FAILURE = "Data file has been modified externally. " +
            "LogJob will start from a blank slate.";
    private static final Logger logger = RootLogger.getLogger();
    private final String filePath;
    private File file;

    /**
     * Constructs a StorageManager with a specified file path.
     *
     * @param filePath The path to the data file.
     */
    public StorageManager(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Constructs a StorageManager using the default file path.
     */
    public StorageManager() {
        this.filePath = DEFAULT_FILE_PATH;
        file = new File(filePath);
    }

    /**
     * Reads application data from the storage file, validating integrity via checksum.
     *
     * @return A list of InternshipApplication instances.
     * @throws StorageException If the file does not pass checksum validation or cannot be accessed.
     * @throws InvalidDelimitedStringException If the file contains malformed data.
     * @throws FileNotFoundException If the file is not found.
     */
    @Override
    public ArrayList<InternshipApplication> readFromFile()
            throws StorageException, InvalidDelimitedStringException, FileNotFoundException {
        // Defensive Checks
        ensureFileExists();
        assert file.exists() : FILE_NOT_FOUND_FAILURE;
        logger.info("Reading applications from file " + filePath);
        Scanner fileScanner = new Scanner(file);
        ArrayList<InternshipApplication> applicationsList = new ArrayList<>();
        if (!fileScanner.hasNextLine()) {
            logger.info("No data found in file " + filePath);
            fileScanner.close();
            return applicationsList;
        }

        String sourceCheckSum = fileScanner.nextLine().trim();
        StringBuilder totalString = new StringBuilder();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            InternshipApplication application = ApplicationSerializer.delimitedStringToApplication(line);
            applicationsList.add(application);
            totalString.append(line);
        }
        fileScanner.close();

        if (!HashUtil.isValidHash(sourceCheckSum, totalString)) {
            throw new StorageException(CHECKSUM_FAILURE);
        }
        logger.info("Successfully read applications from file " + filePath);
        return applicationsList;
    }

    /**
     * Writes a list of applications to the storage file, generating a checksum for data integrity.
     *
     * @param applications The list of applications to store.
     * @throws StorageException If there is an error during the write process.
     */
    @Override
    public void storeToFile(ArrayList<ReadOnlyApplication> applications) throws StorageException {
        ensureFileExists();
        assert file.exists() : FILE_NOT_FOUND_FAILURE;
        logger.info("Storing applications to file " + filePath);
        FileWriter fileWriter = openWriter(file);

        if (applications.isEmpty()) {
            logger.info("No data found in file " + filePath);
            closeWriter(fileWriter);
            return;
        }

        ArrayList<String> printStrings = new ArrayList<>();
        StringBuilder totalString = new StringBuilder();
        for (ReadOnlyApplication readOnlyApplication : applications) {
            InternshipApplication application = readOnlyApplication.getApplication();
            String applicationStorageString = ApplicationSerializer.applicationToDelimitedString(application);
            printStrings.add(applicationStorageString);
            totalString.append(applicationStorageString.trim());
        }

        String checkSumString = HashUtil.generateHash(totalString);
        writeToFile(checkSumString, fileWriter);
        for (String printString : printStrings) {
            writeToFile(printString, fileWriter);
        }

        closeWriter(fileWriter);
        logger.info("Successfully stored applications to file " + filePath);
    }

    /**
     * Ensures that the file exists. If it does not, attempts to create a new file.
     *
     * @throws StorageException if the file cannot be created.
     */
    private void ensureFileExists() throws StorageException {
        if (file.exists()) {
            logger.info("File already exists " + filePath);
            return;
        }
        logger.info("Creating file " + filePath);
        createFile();
    }

    /**
     * Creates the file if it does not exist.
     *
     * @throws StorageException if an I/O error occurs while creating the file.
     */
    private void createFile() throws StorageException {
        try {
            file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.close();
        } catch (IOException e) {
            logger.warning("LogJob is unable to create file " + filePath);
            throw new StorageException("Could not create file.");
        }
    }

    /**
     * Writes a line of data to the file using the provided FileWriter.
     *
     * @param data   the string data to write.
     * @param writer the {@code FileWriter} used to write to the file.
     * @throws StorageException if an I/O error occurs while writing to the file.
     */
    private void writeToFile(String data, FileWriter writer) throws StorageException {
        try {
            writer.write(data);
            writer.write("\n");
        } catch (IOException e) {
            throw new StorageException("Could not write to file.");
        }
    }

    /**
     * Closes the provided {@code FileWriter}.
     *
     * @param writer the {@code FileWriter} to be closed.
     * @throws StorageException if an I/O error occurs while closing the writer.
     */
    private void closeWriter(FileWriter writer) throws StorageException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new StorageException("Could not close file " + file);
        }
    }

    /**
     * Opens a {@code FileWriter} for the specified file.
     *
     * @param file the file for which to open the writer.
     * @return a {@code FileWriter} instance for the file.
     * @throws StorageException if an I/O error occurs while opening the writer.
     */
    private FileWriter openWriter(File file) throws StorageException {
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new StorageException("Could not open file " + file);
        }
    }
}
