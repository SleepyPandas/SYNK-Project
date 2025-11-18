package use_case.view_tasks_and_habits;

/**
 * Input Boundary for actions which are related to create a task.
 */
public interface ViewTasksAndHabitsInputBoundary {
    /**
     * Executes the create_task use case.
     */
    void execute(ViewTasksAndHabitsInputData createInputData);
}
