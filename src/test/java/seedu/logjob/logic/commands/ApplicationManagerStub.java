package seedu.logjob.logic.commands;

import seedu.logjob.model.ApplicationManager;
import seedu.logjob.model.InternshipApplication;
import seedu.logjob.model.ReadOnlyApplication;

import java.util.ArrayList;

public class ApplicationManagerStub extends ApplicationManager {
    private final ArrayList<InternshipApplication> applications = new ArrayList<>();
    private ArrayList<ReadOnlyApplication> obervableApplications;

    public ApplicationManagerStub() {
        super(new ArrayList<InternshipApplication>());
    }

    @Override
    public void addApplication(InternshipApplication application) {
        applications.add(application);
    }

    @Override
    public ArrayList<ReadOnlyApplication> getArrayList() {
        return obervableApplications;
    }

    @Override
    public boolean contains(InternshipApplication application) {
        return applications.contains(application);
    }

    public ArrayList<InternshipApplication> getApplications() {
        return applications;
    }
}
