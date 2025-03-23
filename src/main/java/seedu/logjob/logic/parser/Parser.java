package seedu.logjob.logic.parser;

import seedu.logjob.logic.commands.Command;

import seedu.logjob.logic.parser.exceptions.ParseException;

public interface Parser<T extends Command> {

    T parse(String input) throws ParseException;
}
