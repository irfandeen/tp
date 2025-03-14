package seedu.duke.ui;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.main.Constants;

/**
 * Handles the printing of table cells for listing of the applications
 * E.g.
 * +----+---------+-----------+------------------+---------------------+
 * | ID | Company | Job Title | Status           | Date of Application |
 * +----+---------+-----------+------------------+---------------------+
 * | 1  | Google  | SWE       | Resume Screening | NULL                |
 * +----+---------+-----------+------------------+---------------------+
 */
public class UiTable {

    public UiTable() {
    }

    /**
     * Takes a 2-dimensional array of strings and print the data as a table on the CLI, row by row.
     *
     * @param data 2-d ArrayList of String.
     */
    public static void printTable(ArrayList<ArrayList<String>> data) throws Exception {
        if (data.isEmpty()) {
            throw new Exception();
        }

        int[] columnWidths = getColumnWidths(data);
        printHorizontalBorder(columnWidths);

        for (ArrayList<String> datum : data) {
            printRow(datum, columnWidths);
            printHorizontalBorder(columnWidths);
        }
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
    private static void printHorizontalBorder(int[] columnWidths) {
        System.out.print("+");
        for (int width : columnWidths) {
            System.out.print("-".repeat(width + 2) + "+");
        }
        System.out.println();
    }

    /**
     * Prints a single row for the table.
     *
     * @param row          An arraylist of string to be displayed as a row in the table.
     * @param columnWidths An array of widths representing the maximum width needed for corresponding columns.
     */
    private static void printRow(ArrayList<String> row, int[] columnWidths) {
        System.out.print("|");
        for (int i = 0; i < row.size(); i++) {
            printCell(row.get(i), columnWidths[i]);
        }
        System.out.println();
    }

    /**
     * Prints a single cell for the table.
     *
     * @param content The content to be inserted into the current cell of the table.
     * @param width   Maximum width needed for the current cell.
     */
    private static void printCell(String content, int width) {
        System.out.print(" " + content + " ".repeat(width - content.length()) + " |");
    }

    // For testing purposes only
    public static void main(String[] args) {
        System.out.println(Constants.LOGO);
        ArrayList<ArrayList<String>> data = new ArrayList<>(List.of(
                Constants.TABLE_HEADER_ARRAYLIST,
                new ArrayList<>(List.of("1", "Google", "SWE", "Resume Screening", "NULL"))
        ));

        try {
            printTable(data);
        } catch (Exception e) {
            System.out.println("Empty Table");
        }
    }
}
