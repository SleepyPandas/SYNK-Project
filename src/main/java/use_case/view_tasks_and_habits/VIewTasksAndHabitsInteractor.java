package use_case.view_tasks_and_habits;

import entities.Task;
import entities.TaskBuilder;

import java.time.LocalDateTime;

public class VIewTasksAndHabitsInteractor implements VIewTasksAndHabitsInputBoundary {
    private VIewTasksAndHabitsUserDataAccessInterface userDataAccess;
    private VIewTasksAndHabitsInputBoundary presenter;

    public VIewTasksAndHabitsInteractor(VIewTasksAndHabitsUserDataAccessInterface userDataAccess, VIewTasksAndHabitsInputBoundary presenter){
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }


    @Override
    public void execute(VIewTasksAndHabitsInputData InputData) {

        String taskName = InputData.getTaskName();
        LocalDateTime deadline = InputData.getDeadline();
        String taskGroup = InputData.getTaskGroup();
        boolean status = InputData.getstatus();
        int priority = InputData.getPriority();

        Task newTask = new TaskBuilder()
                .setTaskName(taskName)
                .setDeadline(deadline)
                .setTaskGroup(taskGroup)
                .setStatus(status)
                .setPriority(priority)
                .build();
    }
}
