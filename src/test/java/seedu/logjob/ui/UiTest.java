package seedu.logjob.ui;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UiTest {
    @Test
    void uiTable_nullInput_expectException() {

        ArrayList<ArrayList<String>> applications = new ArrayList<>();
        applications.add(UiConstants.TABLE_HEADER_ARRAYLIST);

        assertThrows(Exception.class, () -> UiTable.getTable(applications));
    }
}
