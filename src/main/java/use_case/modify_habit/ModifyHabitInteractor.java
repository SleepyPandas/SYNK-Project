package use_case.modify_habit;

import entities.Task;
import entities.TaskBuilder;

import java.time.LocalDateTime;

public class ModifyHabitInteractor implements ModifyHabitInputBoundary {
    private final ModifyHabitOutputBoundary modifyHabitPresenter;
    private final ModifyHabitUserDataAccessInterface userDataAccessObject;

    public ModifyHabitInteractor(ModifyHabitOutputBoundary modifyHabitPresenter, ModifyHabitUserDataAccessInterface userDataAccessObject) {
        this.modifyHabitPresenter = modifyHabitPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(ModifyHabitInputData modifyInputData) {

        String newTaskName = modifyInputData.getNewTaskName();
        int newTaskPriority = modifyInputData.getNewPriority();
        boolean newTaskStatus = modifyInputData.getNewTaskStatus();
        LocalDateTime newDeadline = modifyInputData.getNewDeadline();
        String userID = modifyInputData.getUserID();

        final Task modifiedTask = new TaskBuilder()
                .setTaskName(newTaskName)
                .setDeadline(newDeadline)
                .setPriority(newTaskPriority)
                .setStatus(newTaskStatus).build();
        userDataAccessObject.saveTask(userID, modifiedTask);

        final ModifyHabitOutputData outputData = new ModifyHabitOutputData(newTaskName, newDeadline, newTaskStatus,
                newTaskPriority);
        modifyPresenter.prepareSuccessView(outputData);

    }

    @Override
    public void switchToTaskListView() {
        modifyPresenter.switchToTaskListView();
    }
}

