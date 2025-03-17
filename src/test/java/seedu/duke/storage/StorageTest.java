package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.model.ApplicationStatus;
import seedu.duke.model.InternshipApplication;
import seedu.duke.storage.exceptions.StorageException;

class StorageTest {
    @Test
    // happy path
    void readApplicationsFromFile_fileExists_success() {

    }

    @Test
    // happy path
    void storeApplicationsToFile() {
        Storage storage = new StorageManager();

        InternshipApplication[] applications = new InternshipApplication[3];
        for (int i = 0; i < 3; i++) {
            applications[i] = new InternshipApplication("Google", "SWE", ApplicationStatus.APPLIED);
        }

        try {
            storage.storeApplicationsToFile(applications);
        } catch (StorageException e) {

        }
    }
}
