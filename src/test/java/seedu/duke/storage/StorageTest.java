package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
