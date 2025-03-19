package seedu.duke.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UiTest {
    @Test
    void uiTable_nullInput_expectException() throws Exception {
        ArrayList<ArrayList<String>> input = new ArrayList<>();

        assertThrows(Exception.class, () -> UiTable.printTable(input));
    }
}
