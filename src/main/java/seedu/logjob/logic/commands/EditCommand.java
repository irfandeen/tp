package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;


import java.time.LocalDate;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    private final int editIndex;
    private final String companyName;
    private final String jobTitle;
    private final LocalDate applicationDate;
    private final ApplicationStatus applicationStatus;

    public EditCommand(int editIndex, String companyName, String jobTitle,
                       LocalDate applicationDate, ApplicationStatus status) {
        this.editIndex = editIndex;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.applicationDate = applicationDate;
        this.applicationStatus = status;
    }

    @Override
    public CommandResult execute(ApplicationManager applicationManager, UiMain uiMain)
            throws IndexOutOfBoundsException {
        if (editIndex <= 0 || editIndex > applicationManager.getSize()) {
            throw new IndexOutOfBoundsException("Invalid index. Please enter a valid index in the list.");
        }

        InternshipApplication existingApplication = applicationManager.getApplication(editIndex - 1);
        InternshipApplication editedApplication = new InternshipApplication(
                (companyName != null) ? companyName : existingApplication.getCompanyName(),
                (jobTitle != null) ? jobTitle : existingApplication.getJobTitle(),
                (applicationDate != null) ? applicationDate : existingApplication.getApplicationDate(),
                (applicationStatus != null) ? applicationStatus : existingApplication.getStatus()
        );

        applicationManager.updateApplication(editIndex, editedApplication);
        uiMain.printMessage(
                "Application: "
                        + editedApplication.getCompanyName()
                        + " "
                        + editedApplication.getJobTitle()
                        + " "
                        + editedApplication.getStatusToString()
                        + " Edited Successfully"
        );

        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditCommand otherCommand)) {
            return false;
        }

        return editIndex == otherCommand.editIndex
                && java.util.Objects.equals(companyName, otherCommand.companyName)
                && java.util.Objects.equals(jobTitle, otherCommand.jobTitle)
                && java.util.Objects.equals(applicationDate, otherCommand.applicationDate)
                && java.util.Objects.equals(applicationStatus, otherCommand.applicationStatus);
    }
}
