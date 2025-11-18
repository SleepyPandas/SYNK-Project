package use_case.view_tasks_and_habits;

public interface ViewTasksAndHabitsOutputBoundary {
    /**
     * Prepares the success view for the Create Task Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ViewTasksAndHabitsOutputData outputData);

    /**
     * Prepares the failure view for the Create Task Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
