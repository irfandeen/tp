package seedu.duke.main;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String INTRO_MESSAGE = "Welcome to LogJob!";
    public static final String HELP_MESSAGE = "Hi this is a template help message found in the Constants class!";
    public static final String LOGO = """
                 ___       ________  ________        ___  ________  ________
                |\\  \\     |\\   __  \\|\\   ____\\      |\\  \\|\\   __  \\|\\   __  \\
                \\ \\  \\    \\ \\  \\|\\  \\ \\  \\___|      \\ \\  \\ \\  \\|\\  \\ \\  \\|\\ /_
                 \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\  ___  __ \\ \\  \\ \\  \\\\\\  \\ \\   __  \\
                  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\|\\  \\|\\  \\\\_\\  \\ \\  \\\\\\  \\ \\  \\|\\  \\
                   \\ \\_______\\ \\_______\\ \\_______\\ \\________\\ \\_______\\ \\_______\\
                    \\|_______|\\|_______|\\|_______|\\|________|\\|_______|\\|_______|
                """;

    public static final List<String> TABLE_HEADERS =
            List.of("ID", "Company", "Job Title", "Status", "Date of Application");

    public static final ArrayList<String> TABLE_HEADER_ARRAYLIST = new ArrayList<>(Constants.TABLE_HEADERS);
}
