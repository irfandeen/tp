package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;

import java.text.ParseException;

public interface Parser<T extends Command> {

    T parse(String input) throws ParseException;
}
