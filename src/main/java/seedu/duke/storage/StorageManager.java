package seedu.duke.storage;

import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.InvalidDelimitedStringException;
import seedu.duke.storage.exceptions.StorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager implements Storage {
    private static final String DEFAULT_FILE_PATH = "data.txt";
    private String userPrefsFilePath;
    private File userPrefsFile;

    public StorageManager() {
        this.userPrefsFilePath = DEFAULT_FILE_PATH;
    }

    @Override
    public String getUserPrefsFilePath() {
        return userPrefsFilePath;
    }

    // For future extensions when user can choose where to store data
    @Override
    public void setUserPrefsFilePath(String userPrefsFilePath) {
        this.userPrefsFilePath = userPrefsFilePath;
    }

    @Override
    public InternshipApplication[] readApplicationsFromFile() throws StorageException, InvalidDelimitedStringException, FileNotFoundException {
        requireNonNullFile();
        Scanner scanner = new Scanner(userPrefsFile);
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
    public void storeApplicationsToFile(InternshipApplication[] applications) throws IOException, StorageException {
        requireNonNullFile();
        FileWriter fileWriter = new FileWriter(userPrefsFile);

        for (InternshipApplication application : applications) {
            // Serialize to storage friendly format, then store
            String applicationStorageString = ApplicationSerializer.applicationToDelimitedString(application);
            fileWriter.write(applicationStorageString);
            fileWriter.write("\n");
        }
    }

    private void requireNonNullFile() throws StorageException{
        if (userPrefsFile.exists()) {
            return;
        }
        createUserPrefsFile();
    }

    private void createUserPrefsFile() throws StorageException {
        try {
            userPrefsFile = new File(userPrefsFilePath);
            FileWriter writer = new FileWriter(userPrefsFile);
            writer.close();
        } catch (IOException e) {
            throw new StorageException("Could not create file.");
        }
    }
}
