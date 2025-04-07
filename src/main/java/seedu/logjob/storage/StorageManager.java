package seedu.logjob.storage;

import seedu.logjob.model.InternshipApplication;
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

public class StorageManager implements Storage {
    private static final String DEFAULT_FILE_PATH = "data.txt";
    private static final String FILE_NOT_FOUND_FAILURE = "File does not exist && StorageException not thrown";
    private static final String CHECKSUM_FAILURE = "Data file has been modified externally. LogJob will start from a blank slate.";
    private static final Logger logger = RootLogger.getLogger();
    private final String filePath;
    private File file;

    public StorageManager(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public StorageManager() {
        this.filePath = DEFAULT_FILE_PATH;
        file = new File(filePath);
    }

    @Override
    public ArrayList<InternshipApplication> readFromFile()
            throws StorageException, InvalidDelimitedStringException, FileNotFoundException {
        // Defensive Checks
        requireNonNullFile();
        assert file.exists() : FILE_NOT_FOUND_FAILURE;
        logger.info("Reading applications from file " + filePath);

        Scanner fileScanner = new Scanner(file);
        ArrayList<InternshipApplication> applicationsList = new ArrayList<>();

        // Read checksum
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

    @Override
    public void storeToFile(InternshipApplication[] applications) throws StorageException {
        requireNonNullFile();
        assert file.exists() : FILE_NOT_FOUND_FAILURE;
        logger.info("Storing applications to file " + filePath);
        FileWriter fileWriter = openWriter(file);

        if (applications.length == 0) {
            logger.info("No data found in file " + filePath);
            closeWriter(fileWriter);
            return;
        }

        ArrayList<String> printStrings = new ArrayList<>();
        StringBuilder totalString = new StringBuilder();
        for (InternshipApplication application : applications) {
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

    private void requireNonNullFile() throws StorageException {
        if (file.exists()) {
            logger.info("File already exists " + filePath);
            return;
        }
        logger.info("Creating file " + filePath);
        createUserPrefsFile();
    }

    private void createUserPrefsFile() throws StorageException {
        try {
            file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.close();
        } catch (IOException e) {
            logger.warning("LogJob is unable to create file " + filePath);
            throw new StorageException("Could not create file.");
        }
    }

    private void writeToFile(String data, FileWriter writer) throws StorageException {
        try {
            writer.write(data);
            writer.write("\n");
        } catch (IOException e) {
            throw new StorageException("Could not write to file.");
        }
    }

    private void closeWriter(FileWriter writer) throws StorageException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new StorageException("Could not close file " + file);
        }
    }

    private FileWriter openWriter(File file) throws StorageException {
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new StorageException("Could not open file " + file);
        }
    }
}
