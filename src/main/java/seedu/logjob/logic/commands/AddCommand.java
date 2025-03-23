package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;

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
    public void execute(ApplicationManager applicationManager, UiMain uiMain) {
        InternshipApplication newApplication = new InternshipApplication(companyName, jobTitle, status);
        applicationManager.addApplication(newApplication, uiMain);
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
