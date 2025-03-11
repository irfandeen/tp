package seedu.duke.logic.parser;

/**
 * A flag marks the start of an argument in the command string.
 * E.g. "-n" in "add -n Google -j Software Engineer [-s Applied]"
 */
public class Flag {
    private String flag;

    public Flag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return getFlag();
    }
}
