package seedu.logjob.ui;

import java.util.ArrayList;
import java.util.List;

public class UiConstants {
    public static final String INTRO_MESSAGE = "Welcome to LogJob!";
    public static final String HELP_MESSAGE = """   
    
    +---------+----------------------------------------------------------------+
    |                       | LIST OF IMPORTANT FLAGS |                        |
    +---------+----------------------------------------------------------------+
    |  Flag   | Description                                                    |
    |   -n    | Company Name.                                                  |
    |   -j    | Job Title.                                                     |
    |   -s    | Application Status.                                            |
    |   -d    | Date of Application.                                           |
    +---------+----------------------------------------------------------------+
    
         
    +---------+----------------------------------------------------------------+
    | Command | Description                                                    |
    +---------+----------------------------------------------------------------+
    | help    | Shows this help message.                                       |
    +---------+----------------------------------------------------------------+
    | add     | Add a job application.                                         |
    |           Flags: -n, -j, -s, -d.                                         |
    |           Example: add -n Google -j SWE -s INTERVIEW -d 2025-12-01       |
    +---------+----------------------------------------------------------------+
    | delete  | Delete the application at INDEX.                               |
    |           Example: delete 2                                              |
    +---------+----------------------------------------------------------------+
    | edit    | Edit application at INDEX. Requires at least one flag.         |
    |           Flags: -n, -j, -s, -d.                                         |
    |           Example: edit 1 -n Meta -j Backend                             |
    +---------+----------------------------------------------------------------+
    | find    | Search applications by name or job.                            |
    |           Example: find Embedded                                         |
    +---------+----------------------------------------------------------------+
    | sort    | Sort applications by Company Name or Application Date.         |
    |           Flags: -n, -d.                                                 |
    |           Example: sort -d                                               |
    +---------+----------------------------------------------------------------+
    | list    | List all applications.                                         |
    +---------+----------------------------------------------------------------+
    | exit    | Quit the app.                                                  |
    +---------+----------------------------------------------------------------+
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
