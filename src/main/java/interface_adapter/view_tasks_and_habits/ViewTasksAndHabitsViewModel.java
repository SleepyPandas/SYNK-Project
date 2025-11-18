package interface_adapter.view_tasks_and_habits;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class ViewTasksAndHabitsViewModel extends ViewModel<ViewTasksAndHabitsState> {

    public ViewTasksAndHabitsViewModel() {
        super("log in");
        setState(new ViewTasksAndHabitsState());
    }

}
