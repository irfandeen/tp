package seedu.logjob.logic;

import org.junit.jupiter.api.Test;

import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiConstants;
import seedu.logjob.storage.StorageManager;
import seedu.logjob.ui.UiMain;
import seedu.logjob.ui.exceptions.EmptyTableException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicManagerTest {
    private class DummyLogicManager extends LogicManager {
        public DummyLogicManager(StorageManager storageManager, ApplicationManager applicationManager, UiMain uiMain) {
            super(storageManager, applicationManager, uiMain);
        }
    }

    @Test
    public void execute_helpCommandText_expectHelpMessage() throws
            IndexOutOfBoundsException, ParseException, EmptyTableException {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outputStreamCaptor));

        DummyLogicManager dummyLogicManager = new DummyLogicManager(
                new StorageManager(), new ApplicationManager(new ArrayList<>()), UiMain.getInstance());
        dummyLogicManager.execute("help");

        System.setOut(originalOut);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains(UiConstants.HELP_MESSAGE));
    }

    @Test
    public void execute_listCommandText_expectList()
            throws IndexOutOfBoundsException, ParseException, EmptyTableException {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outputStreamCaptor));
        ArrayList<InternshipApplication> applications = new ArrayList<>();
        applications.add(
                new InternshipApplication(
                        "goo",
                        "sss",
                        LocalDate.now(),
                        ApplicationStatus.APPLIED
                )
        );
        DummyLogicManager dummyLogicManager = new DummyLogicManager(
                new StorageManager(),
                new ApplicationManager(applications),
                UiMain.getInstance());
        dummyLogicManager.execute("list");

        System.setOut(originalOut);

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("goo"));
        assertTrue(output.contains("sss"));
    }

    @Test
    public void execute_deleteCommandText_expectException()
            throws IndexOutOfBoundsException, ParseException, EmptyTableException {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outputStreamCaptor));
        DummyLogicManager dummyLogicManager = new DummyLogicManager(
                new StorageManager(), new ApplicationManager(new ArrayList<>()), UiMain.getInstance());
        assertThrows(Exception.class, () -> dummyLogicManager.execute("delete 1"));
    }
}
