package interface_adapter.view_tasks_and_habits;

import use_case.view_tasks_and_habits.ViewTasksAndHabitsInputBoundary;
import use_case.view_tasks_and_habits.ViewTasksAndHabitsInputData;

/**
 * The controller for the Login Use Case.
 */
public class ViewTasksAndHabitsController {

    private final ViewTasksAndHabitsInputBoundary ViewTasksAndHabitsUseCaseInteractor;

    public ViewTasksAndHabitsController(ViewTasksAndHabitsInputBoundary viewTasksAndHabitsUseCaseInteractor) {
        this.ViewTasksAndHabitsUseCaseInteractor = viewTasksAndHabitsUseCaseInteractor;
    }

    /**
     * Executes the View Tasks And Habits Use Case.
     */
    public static void execute(String designation, int col, String completeableName) {
        final ViewTasksAndHabitsInputData viewTasksAndHabitsInputData = new ViewTasksAndHabitsInputData(
                designation, col, completeableName);

        ViewTasksAndHabitsUseCaseInteractor.execute(viewTasksAndHabitsInputData);
    }
}
