package use_case.view_tasks_and_habits;

import java.util.ArrayList;
import interface_adapter.logged_in.LoggedInViewModel;

/**
 * Input Boundary for actions which are related to create a task.
 */
public interface ViewTasksAndHabitsInputBoundary {
    /**
     * Executes the view_tasks_and_habits use case.
     */
    void execute(ViewTasksAndHabitsInputData createInputData);

    ArrayList<ArrayList<String>> getFormattedTasks(LoggedInViewModel loggedInViewModel);

    ArrayList<ArrayList<String>> getFormattedHabits(LoggedInViewModel loggedInViewModel);
}
