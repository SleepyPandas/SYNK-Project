package use_case.view_tasks_and_habits;

/**
 * Input Boundary for actions which are related to create a task.
 */
public interface VIewTasksAndHabitsInputBoundary {
    /**
     * Executes the create_task use case.
     * @param createTaskInputData the input data
     */
    void execute(VIewTasksAndHabitsInputData createTaskInputData);
}
