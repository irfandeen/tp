package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.UiTable;

import java.util.ArrayList;

public class JUnitTest {
    @Test
    void uiTable_nullInput_expectException() throws Exception {
        UiTable uiTable = new UiTable();
        ArrayList<ArrayList<String>> input = new ArrayList<>();

        assertThrows(Exception.class, () -> UiTable.printTable(input));
    }
}
