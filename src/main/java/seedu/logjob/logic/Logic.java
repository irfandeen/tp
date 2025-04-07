package seedu.logjob.logic;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.logic.parser.exceptions.ParseException;
import seedu.logjob.ui.exceptions.EmptyTableException;

/*
    API of Logic Component that defines interactions with main class
 */
public interface Logic {
    /**
     * Executes the input command.
     * @param commandText command as entered by user
     */
    void execute(String commandText) throws IndexOutOfBoundsException, ParseException, EmptyTableException;

    boolean getIsRunning();
}
