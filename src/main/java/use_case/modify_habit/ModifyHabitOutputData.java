package use_case.modify_habit;

import java.time.LocalDateTime;

public class ModifyHabitOutputData {
    private final String newTaskName;
    private final LocalDateTime newDeadline;
    private final boolean newTaskStatus;
    private final int newTaskPriority;

    public ModifyHabitOutputData(String newTaskName, LocalDateTime newDeadline, boolean newTaskStatus, int newTaskPriority) {
        this.newTaskName = newTaskName;
        this.newDeadline = newDeadline;
        this.newTaskStatus = newTaskStatus;
        this.newTaskPriority = newTaskPriority;
    }

    public String getNewTaskName() {
        return newTaskName;
    }

    public LocalDateTime getNewDeadline() {
        return newDeadline;
    }

    public boolean getNewTaskStatus() {
        return newTaskStatus;
    }

    public int getNewTaskPriority() {return newTaskPriority;}
}
