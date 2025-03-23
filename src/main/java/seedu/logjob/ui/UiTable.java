package seedu.logjob.ui;

import java.util.ArrayList;

import seedu.logjob.ui.exceptions.EmptyTableException;


/**
 * Handles the printing of table cells for listing of the applications
 * E.g.
 * +----+---------+-----------+------------------+---------------------+
 * | ID | Company | Job Title | Status           | Date of Application |
 * +----+---------+-----------+------------------+---------------------+
 * | 1  | Google  | SWE       | Resume Screening | NULL                |
 * +----+---------+-----------+------------------+---------------------+
 */
public final class UiTable {
    /**
     * Takes a 2-dimensional array of strings and print the data as a table on the CLI, row by row.
     *
     * @param data 2-d ArrayList of String.
     */
    public static String getTable(ArrayList<ArrayList<String>> data) throws EmptyTableException {
        assert data != null : "Data should not be null";

        if (data.size() <= 1) {
            throw new EmptyTableException("Table is empty");
        }

        StringBuilder table = new StringBuilder();
        int[] columnWidths = getColumnWidths(data);
        table.append(getHorizontalBorder(columnWidths));

        for (ArrayList<String> datum : data) {
            table.append(getRow(datum, columnWidths));
            table.append(getHorizontalBorder(columnWidths));
        }

        return table.toString();
    }

    /**
     * Determines the largest column width needed for the table by checking each column,
     * finding the string with maximum length
     *
     * @param data 2-d ArrayList of String.
     * @return An array of widths representing the maximum width needed for corresponding columns.
     */
    private static int[] getColumnWidths(ArrayList<ArrayList<String>> data) {
        int columns = data.get(0).size();
        int[] widths = new int[columns];

        for (ArrayList<String> row : data) {
            for (int i = 0; i < columns; i++) {
                widths[i] = Math.max(widths[i], row.get(i).length());
            }
        }
        return widths;
    }

    /**
     * Prints the horizontal border of the cells for the table.
     *
     * @param columnWidths An array of widths representing the maximum width needed for corresponding columns.
     */
    private static String getHorizontalBorder(int[] columnWidths) {
        StringBuilder border = new StringBuilder();
        border.append("+");
        for (int width : columnWidths) {
            border.append("-".repeat(width + 2));
            border.append("+");
        }
        border.append("\n");

        return border.toString();
    }

    /**
     * Prints a single row for the table.
     *
     * @param row          An arraylist of string to be displayed as a row in the table.
     * @param columnWidths An array of widths representing the maximum width needed for corresponding columns.
     */
    private static String getRow(ArrayList<String> row, int[] columnWidths) {
        StringBuilder tableRow = new StringBuilder();
        tableRow.append("|");
        for (int i = 0; i < row.size(); i++) {
            tableRow.append(getCell(row.get(i), columnWidths[i]));
        }
        tableRow.append("\n");
        return tableRow.toString();
    }

    /**
     * Prints a single cell for the table.
     *
     * @param content The content to be inserted into the current cell of the table.
     * @param width   Maximum width needed for the current cell.
     */
    private static String getCell(String content, int width) {
        assert content != null : "Cell content should not be null";

        return " " + content + " ".repeat(width - content.length()) + " |";
    }

}
