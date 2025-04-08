package seedu.logjob.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Provides a centralized {@link Logger} instance for the LogJob application.
 * <p>
 * This logger writes all logs to a file named <code>LogJob.log</code>. Logging is configured
 * to not append to previous logs and to disable parent handlers to keep log output isolated.
 */
public class RootLogger {
    private static final Logger logger = Logger.getLogger("LogJob");
    private static final String LOG_FILE_NAME = "LogJob.log";
    private static final boolean USE_PARENT_HANDLERS = false;
    private static final boolean IS_APPEND_TO_LOG = false;

    static {
        try {
            // Log gets refreshed at every run in order to minimize memory
            FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, IS_APPEND_TO_LOG);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(USE_PARENT_HANDLERS);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            // Failure to set up logger should not disrupt program execution
            System.out.println("Error setting up logging. Resuming execution.");
        }
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private RootLogger() {}

    /**
     * Returns the shared {@link Logger} instance used throughout the LogJob application.
     *
     * @return the application's global logger instance.
     */
    public static Logger getLogger() {
        return logger;
    }
}
