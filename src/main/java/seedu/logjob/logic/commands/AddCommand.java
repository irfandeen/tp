package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;

import java.time.LocalDate;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_ADD_SUCCESS = "Application: %s %s %s Added Successfully";
    public static final String MESSAGE_ADD_DUPLICATE = "Add failed. This application already exists.";

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
    public CommandResult execute(ApplicationManager applicationManager) {
        InternshipApplication newApplication =
                new InternshipApplication(companyName, jobTitle, applicationDate, applicationStatus);
        applicationManager.addApplication(newApplication);

        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS,
                newApplication.getCompanyName(),
                newApplication.getJobTitle(),
                newApplication.getStatusToString())
                , false, false);
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
