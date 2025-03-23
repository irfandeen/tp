package seedu.LogJob.storage;

import seedu.LogJob.model.InternshipApplication;
import seedu.LogJob.storage.exceptions.InvalidDelimitedStringException;
import seedu.LogJob.storage.exceptions.StorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager implements Storage {
    private static final String DEFAULT_FILE_PATH = "data.txt";
    private final String filePath;
    private File file;
    private static final String FILE_NOT_FOUND_FAILURE = "File does not exist && StorageException not thrown";

    public StorageManager(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public StorageManager() {
        this.filePath = DEFAULT_FILE_PATH;
        file = new File(filePath);
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public ArrayList<InternshipApplication> readApplicationsFromFile()
            throws StorageException, InvalidDelimitedStringException, FileNotFoundException {
        requireNonNullFile();
        assert file.exists() : FILE_NOT_FOUND_FAILURE;
        Scanner fileScanner = new Scanner(file);
        ArrayList<InternshipApplication> applicationsList = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            InternshipApplication application = ApplicationSerializer.delimitedStringToApplication(line);
            applicationsList.add(application);
        }

        fileScanner.close();
        return applicationsList;
    }

    @Override
    public void storeApplicationsToFile(InternshipApplication[] applications) throws StorageException {
        requireNonNullFile();
        assert file.exists() : FILE_NOT_FOUND_FAILURE;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new StorageException("Could not open file " + filePath);
        }

        // Serialize to storage friendly format, then store
        for (InternshipApplication application : applications) {
            String applicationStorageString = ApplicationSerializer.applicationToDelimitedString(application);
            writeToFile(applicationStorageString, fileWriter);
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageException("Could not close file " + file);
        }
    }

    private void requireNonNullFile() throws StorageException {
        if (file.exists()) {
            return;
        }
        createUserPrefsFile();
    }

    private void createUserPrefsFile() throws StorageException {
        try {
            file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            writer.close();
        } catch (IOException e) {
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
}
