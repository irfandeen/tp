package seedu.LogJob.logic.parser;

/**
 * A flag marks the start of an argument in the command string.
 * E.g. "-n" in "add -n Google -j Software Engineer [-s Applied]"
 */
public record Flag(String flag) {

    @Override
    public String toString() {
        return flag();
    }
}
