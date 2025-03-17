package seedu.duke.storage;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;
import seedu.duke.storage.exceptions.StorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager implements Storage {
    private static final String DEFAULT_FILE_PATH = "data.txt";
    private String filePath;
    private File file;

    public StorageManager() {
        this.filePath = DEFAULT_FILE_PATH;
        file = new File(filePath);
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    // For future extensions when user can choose where to store data
    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InternshipApplication[] readApplicationsFromFile()
            throws StorageException, InvalidDelimitedStringException, FileNotFoundException {
        requireNonNullFile();
        Scanner scanner = new Scanner(file);
        ArrayList<InternshipApplication> applicationsList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            InternshipApplication application = ApplicationSerializer.delimitedStringToApplication(line);
            applicationsList.add(application);
        }

        scanner.close();
        return applicationsList.toArray(new InternshipApplication[0]);
    }

    @Override
    public void storeApplicationsToFile(InternshipApplication[] applications) throws StorageException {
        requireNonNullFile();
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
