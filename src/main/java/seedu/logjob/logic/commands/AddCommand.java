package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;

import java.time.LocalDate;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private final String companyName;
    private final String jobTitle;
    private final LocalDate applicationDate;
    private final ApplicationStatus applicationStatus;

    public AddCommand(String companyName, String jobTitle, LocalDate applicationDate, ApplicationStatus status) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.applicationDate = applicationDate;
        this.applicationStatus = status;
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain) {
        InternshipApplication newApplication =
                new InternshipApplication(companyName, jobTitle, applicationDate, applicationStatus, applicationManager.getSize() + 1);
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
                && applicationStatus.equals(otherCommand.applicationStatus);
    }
}
