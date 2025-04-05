package seedu.logjob.ui;

import java.util.ArrayList;
import java.util.List;

public class UiConstants {
    public static final String INTRO_MESSAGE = "Welcome to LogJob!";
    public static final String HELP_MESSAGE = """
            LogJob Help
            
            Legend:
            UPPER_CASE: required | [brackets]: optional
            DATE format: yyyy-MM-dd | INDEX: from 'list'
            
            help
                Shows this help message.
            
            add -n COMPANY_NAME -j JOB_TITLE [-s STATUS] [-d APPLICATION_DATE]
                Add a job application. Flags: -n, -j, -s, -d.
                Example: add -n Google -j SWE -s INTERVIEW -d 2025-12-01
            
            delete INDEX
                Delete the application at INDEX.
                Example: delete 2
            
            edit INDEX [FLAGS...]
                Edit application at INDEX. Requires at least one flag. Flags: -n, -j, -s, -d.
                Example: edit 1 -n Meta -j Backend
            
            find KEYWORD
                Search applications by name or job.
                Example: find Embedded Engineering Intern
            
            sort FLAG
                Sort applications by either Company Name or Application Date. FLags: -n, -d.
                Example: sort -d
            
            list
                List all applications.
            
            exit
                Save and quit the app.
            
            """;
    public static final String LOGO = """
                 ___       ________  ________        ___  ________  ________
                |\\  \\     |\\   __  \\|\\   ____\\      |\\  \\|\\   __  \\|\\   __  \\
                \\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|      \\ \\  \\ \\  \\|\\  \\ \\  \\|\\ /_
                 \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  ___  __ \\ \\  \\ \\  \\\\\\  \\ \\   __  \\
                  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\|\\  \\\\_\\  \\ \\  \\\\\\  \\ \\  \\|\\  \\
                   \\ \\_______\\ \\_______\\ \\_______\\ \\________\\ \\_______\\ \\_______\\
                    \\|_______|\\|_______|\\|_______|\\|________|\\|_______|\\|_______|
                """;

    public static final ArrayList<String> TABLE_HEADER_ARRAYLIST = new ArrayList<>(
            List.of("ID", "Company", "Job Title", "Status", "Date of Application"));

    public static final String LINE_BREAK = "----------------------------------------";
}
