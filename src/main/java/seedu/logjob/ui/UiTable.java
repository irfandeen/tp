package seedu.logjob.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.logjob.model.InternshipApplication;
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
     * Takes an array of applications and return the data as a table on the CLI.
     *
     * @param applicationList ArrayList of applications.
     */
    public static String getTable(ArrayList<InternshipApplication> applicationList) throws EmptyTableException {
        assert applicationList != null : "Data should not be null";
        if (applicationList.isEmpty()) {
            throw new EmptyTableException("Table is empty");
        }

        ArrayList<ArrayList<String>> applications = new ArrayList<>();
        applications.add(UiConstants.TABLE_HEADER_ARRAYLIST);
        for (int i = 0; i < applicationList.size(); i++) {
            LocalDate applicationDate = applicationList.get(i).getApplicationDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String applicationDateString = applicationDate.format(formatter);

            ArrayList<String> applicationRow = new ArrayList<>();
            applicationRow.add(Integer.toString(i));
            applicationRow.add(applicationList.get(i).getCompanyName());
            applicationRow.add(applicationList.get(i).getJobTitle());
            applicationRow.add(applicationList.get(i).getStatusToString());
            applicationRow.add(applicationDateString);
            applications.add(applicationRow);
        }

        StringBuilder table = new StringBuilder();
        int[] columnWidths = getColumnWidths(applications);
        table.append(getHorizontalBorder(columnWidths));

        for (ArrayList<String> application : applications) {
            table.append(getRow(application, columnWidths));
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
