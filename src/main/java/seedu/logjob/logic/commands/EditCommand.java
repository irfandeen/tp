package seedu.logjob.logic.commands;

import seedu.logjob.logic.commands.exceptions.IndexOutOfBoundsException;
import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.ApplicationStatus;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.ui.UiMain;


import java.time.LocalDate;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    private final int editId;
    private final String companyName;
    private final String jobTitle;
    private final LocalDate applicationDate;
    private final ApplicationStatus applicationStatus;

    public EditCommand(int editId, String companyName, String jobTitle,
                       LocalDate applicationDate, ApplicationStatus status) {
        this.editId = editId;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.applicationDate = applicationDate;
        this.applicationStatus = status;
    }

    @Override
    public void execute(ApplicationManager applicationManager, UiMain uiMain)
            throws IndexOutOfBoundsException {
        if (editId < 1 || editId > applicationManager.getSize()) {
            throw new IndexOutOfBoundsException("Invalid ID. Please enter a valid ID in the list.");
        }

        InternshipApplication existingApplication = applicationManager.getApplication(editId);
        InternshipApplication editedApplication = new InternshipApplication(
                (companyName != null) ? companyName : existingApplication.getCompanyName(),
                (jobTitle != null) ? jobTitle : existingApplication.getJobTitle(),
                (applicationDate != null) ? applicationDate : existingApplication.getApplicationDate(),
                (applicationStatus != null) ? applicationStatus : existingApplication.getStatus(),
                existingApplication.getId()
        );

        applicationManager.updateApplication(editId, editedApplication);
        uiMain.printMessage(
                "Application: "
                        + editedApplication.getCompanyName()
                        + " "
                        + editedApplication.getJobTitle()
                        + " "
                        + editedApplication.getStatusToString()
                        + " Edited Successfully"
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditCommand otherCommand)) {
            return false;
        }

        return editId == otherCommand.editId
                && java.util.Objects.equals(companyName, otherCommand.companyName)
                && java.util.Objects.equals(jobTitle, otherCommand.jobTitle)
                && java.util.Objects.equals(applicationDate, otherCommand.applicationDate)
                && java.util.Objects.equals(applicationStatus, otherCommand.applicationStatus);
    }
}
