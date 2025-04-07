package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;


import java.time.LocalDate;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    private static final String MESSAGE_EDIT_SUCCESS = "Application: %s %s %s Edited Successfully";
    private static final String MESSAGE_OUT_OF_BOUNDS = "Invalid index. Please enter a valid index in the list.";

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
            throw new IndexOutOfBoundsException(MESSAGE_OUT_OF_BOUNDS);
        }

        InternshipApplication existingApplication = applicationManager.getApplication(editIndex - 1);
        InternshipApplication editedApplication = new InternshipApplication(
                (companyName != null) ? companyName : existingApplication.getCompanyName(),
                (jobTitle != null) ? jobTitle : existingApplication.getJobTitle(),
                (applicationDate != null) ? applicationDate : existingApplication.getApplicationDate(),
                (applicationStatus != null) ? applicationStatus : existingApplication.getStatus()
        );

        applicationManager.updateApplication(editIndex, editedApplication);

        return new CommandResult(String.format(MESSAGE_EDIT_SUCCESS,
                editedApplication.getCompanyName(),
                editedApplication.getJobTitle(),
                editedApplication.getStatusToString()),
                false, false);
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
