package seedu.LogJob.logic.parser;

import seedu.LogJob.logic.commands.Command;

import seedu.LogJob.logic.parser.exceptions.ParseException;

public interface Parser<T extends Command> {

    T parse(String input) throws ParseException;
}
