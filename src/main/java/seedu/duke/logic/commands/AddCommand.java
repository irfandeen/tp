package seedu.duke.logic.commands;

import seedu.duke.model.ApplicationManager;
import seedu.duke.model.ApplicationStatus;
import seedu.duke.model.InternshipApplication;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private final String companyName;
    private final String jobTitle;
    private final ApplicationStatus status;

    public AddCommand(String companyName, String jobTitle, ApplicationStatus status) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    public AddCommand(String companyName, String jobTitle) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.status = ApplicationStatus.APPLIED;
    }

    @Override
    public void execute(ApplicationManager applicationManager) {
        InternshipApplication newApplication = new InternshipApplication(companyName, jobTitle, status);
        applicationManager.addApplication(newApplication);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddCommand otherCommand)) {
            return false;
        }

        return companyName.equals(otherCommand.companyName)
                && jobTitle.equals(otherCommand.jobTitle)
                && status.equals(otherCommand.status);
    }
}
