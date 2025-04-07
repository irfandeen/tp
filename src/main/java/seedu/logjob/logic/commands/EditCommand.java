package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.logic.commands.exceptions.DuplicateApplicationException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;


import java.time.LocalDate;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    private static final String MESSAGE_EDIT_SUCCESS = "Application: %s %s %s Edited Successfully";
    private static final String MESSAGE_OUT_OF_BOUNDS = "Invalid index. Please enter a valid index in the list.";
    private static final String MESSAGE_EDIT_UNCHANGED = "Edit skipped. No fields were changed in this edit.";
    private static final String MESSAGE_EDIT_DUPLICATE = "Edit failed. This application already exists.";

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
    public CommandResult execute(ApplicationManager applicationManager)
            throws IndexOutOfBoundsException, DuplicateApplicationException {
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

        // Check whether editedApplication has any changed fields compared to existingApplication
        if (editedApplication.equals(existingApplication)) {
            throw new DuplicateApplicationException(MESSAGE_EDIT_UNCHANGED);
        }

        // Check whether editedApplication is a duplicate of other applications in the list
        if (applicationManager.contains(editedApplication)) {
            throw new DuplicateApplicationException(MESSAGE_EDIT_DUPLICATE);
        }

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
