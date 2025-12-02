package interface_adapter.view_tasks_and_habits;

import interface_adapter.logged_in.LoggedInViewModel;
import use_case.view_tasks_and_habits.ViewTasksAndHabitsInputBoundary;
import use_case.view_tasks_and_habits.ViewTasksAndHabitsInputData;

/**
 * The controller for the View Tasks and Habits Use Case.
 */
public class ViewTasksAndHabitsController {

    private final ViewTasksAndHabitsInputBoundary viewTasksAndHabitsUseCaseInteractor;
    private final ViewTasksAndHabitsInputData viewTasksAndHabitsInputData;

    public ViewTasksAndHabitsController(ViewTasksAndHabitsInputBoundary viewTasksAndHabitsUseCaseInteractor,
                                        ViewTasksAndHabitsInputData viewTasksAndHabitsInputData,
                                        LoggedInViewModel loggedInViewModel) {
        this.viewTasksAndHabitsUseCaseInteractor = viewTasksAndHabitsUseCaseInteractor;
        this.viewTasksAndHabitsInputData = new ViewTasksAndHabitsInputData(loggedInViewModel.getState().getUsername());
    }

    /**
     * Calls the respective method in the interactor to execute the use case.
     * @param inputData the view model for the logged in use case
     */
    public void getFormattedTasksAndHabits(ViewTasksAndHabitsInputData inputData) {
        viewTasksAndHabitsUseCaseInteractor.getFormattedTasksAndHabits(inputData);
    }
}
