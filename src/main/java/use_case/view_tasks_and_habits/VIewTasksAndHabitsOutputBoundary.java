package use_case.view_tasks_and_habits;

public interface VIewTasksAndHabitsOutputBoundary {
    /**
     * Prepares the success view for the Create Task Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(VIewTasksAndHabitsOutputData outputData);

    /**
     * Prepares the failure view for the Create Task Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
