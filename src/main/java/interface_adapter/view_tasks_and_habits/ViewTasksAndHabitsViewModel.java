package interface_adapter.view_tasks_and_habits;

import interface_adapter.ViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

/**
 * The View Model for the Login View.
 */
public class ViewTasksAndHabitsViewModel extends ViewModel<ViewTasksAndHabitsState> {

    public static final String[] taskCols = {"Task", "Deadline", "Task Group", "Status", "Priority"};
    public static final String[] habitCols = {"Habit", "Start Date", "Start Time", "Frequency", "Habit Group", "Streak Count", "Priority"};

    public static Object[] sampleTask = new Object[] {"Work", LocalDateTime.of(2025, 10, 10, 10,
            0), "Important", "Not Finished", 1};
    public static Object[] sampleHabit = new Object[] {"Gym", LocalDate.of(2025, 10, 10), LocalTime.of(10,
            10), Period.of(0, 0, 1), "Important", 1, 1};


    public ViewTasksAndHabitsViewModel() {
        super("view tasks and habits");
        setState(new ViewTasksAndHabitsState());
    }

}
