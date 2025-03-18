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
    public void execute() {
        InternshipApplication newApplication = new InternshipApplication(companyName, jobTitle, status);
        ApplicationManager.addApplication(newApplication);
    }
}
