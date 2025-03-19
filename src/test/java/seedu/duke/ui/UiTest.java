package seedu.duke.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.duke.ui.exceptions.EmptyTableException;

import java.util.ArrayList;

public class UiTest {
    @Test
    void uiTable_nullInput_expectException() {
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        UiTable uiTable = new UiTable();
        assertThrows(EmptyTableException.class, () -> uiTable.printTable(input));
    }
}
