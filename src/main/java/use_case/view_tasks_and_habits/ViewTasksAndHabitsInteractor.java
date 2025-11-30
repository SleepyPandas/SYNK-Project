package use_case.view_tasks_and_habits;


import data_access.FileUserDataAccessObject;
import data_access.HabitDataAccessObject;
import data_access.TaskDataAccessObject;
import entities.Task;
import entities.Habit;

import interface_adapter.logged_in.LoggedInViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ViewTasksAndHabitsInteractor implements ViewTasksAndHabitsInputBoundary {
    private FileUserDataAccessObject userDataAccess;
    private TaskDataAccessObject taskDataAccess;
    private HabitDataAccessObject habitDataAccess;
    private ViewTasksAndHabitsOutputBoundary presenter;

    public ViewTasksAndHabitsInteractor(TaskDataAccessObject taskDataAccess, HabitDataAccessObject habitDataAccess, FileUserDataAccessObject userDataAccess, ViewTasksAndHabitsOutputBoundary presenter){
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
        this.taskDataAccess = taskDataAccess;
        this.habitDataAccess = habitDataAccess;
    }

    @Override
    public void execute(ViewTasksAndHabitsInputData InputData) {

    }

    public void getFormattedTasksAndHabits(LoggedInViewModel loggedInViewModel) {
        try {
            ArrayList<Task> taskList = this.taskDataAccess.fetchTasks(loggedInViewModel.getState().getUsername());

            ArrayList<ArrayList<String>> formattedTasks = new ArrayList<>();

            for (Task task : taskList) {
                ArrayList<String> formattedTask = new ArrayList<>();

                String taskName = task.getName();
                LocalDateTime taskDeadline = task.getDueDate();
                LocalDateTime taskStartTime = task.getStartTime();
                String taskGroup = task.getTaskGroup();
                boolean status = task.getStatus();
                int priority = task.getPriority();
                String description = task.getDescription();

                formattedTask.add(taskName);

                final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM, yyyy HH:mm", Locale.ENGLISH);
                formattedTask.add(taskStartTime.format(OUTPUT_FORMAT));
                String taskDeadlineToString = taskDeadline.toString();
                switch (taskDeadlineToString.substring(5, 7)) {
                    case "01":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " January, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "02":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " February, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "03":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " March, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "04":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " April, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "05":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " May, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "06":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " June, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "07":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " July, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "08":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " August, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "09":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " September, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "10":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " October, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "11":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " November, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                    case "12":
                        formattedTask.add(taskDeadlineToString.substring(8, 10) + " December, " +
                                taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                        break;
                }

                formattedTask.add(taskGroup);

                if (status == true) {
                    formattedTask.add("Complete");
                } else {
                    formattedTask.add("Incomplete");
                }

                formattedTask.add(Integer.toString(priority));

                formattedTask.add(description);

                formattedTasks.add(formattedTask);
            }
            ArrayList<Habit> habitList = this.habitDataAccess.fetchHabits(loggedInViewModel.getState().getUsername());

            ArrayList<ArrayList<String>> formattedHabits = new ArrayList<>();

            for (Habit habit : habitList) {
                ArrayList<String> formattedHabit = new ArrayList<>();

                String habitName = habit.getName();
                LocalDateTime habitStartDateTime = habit.getStartTime();
                int habitFrequency = habit.getFrequency();
                String habitGroup = habit.getHabitGroup();
                int habitStreakCount = habit.getStreakCount();
                int priority = habit.getPriority();
                boolean status = habit.getStatus();
                String description = habit.getDescription();

                formattedHabit.add(habitName);

                String habitStartDateTimeToString = habitStartDateTime.toString();
                switch (habitStartDateTimeToString.substring(5, 7)) {
                    case "01":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " January, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "02":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " February, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "03":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " March, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "04":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " April, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "05":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " May, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "06":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " June, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "07":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " July, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "08":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " August, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "09":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " September, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "10":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " October, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "11":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " November, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                    case "12":
                        formattedHabit.add(habitStartDateTimeToString.substring(8, 10) + " December, " +
                                habitStartDateTimeToString.substring(0, 4) + " " + habitStartDateTimeToString.substring(11, 16));
                        break;
                }

                formattedHabit.add(Integer.toString(habitFrequency));

                formattedHabit.add(habitGroup);
                formattedHabit.add(Integer.toString(habitStreakCount));

                formattedHabit.add(Integer.toString(priority));

                if (status == true) {
                    formattedHabit.add("Complete");
                } else {
                    formattedHabit.add("Incomplete");
                }

                formattedHabit.add(description);

                formattedHabits.add(formattedHabit);
            }
            ViewTasksAndHabitsOutputData outputData = new ViewTasksAndHabitsOutputData(formattedTasks, formattedHabits);
            presenter.prepareSuccessView(outputData);
        }
        catch (Exception e) {
            presenter.prepareFailView("Failed to load task and habit data");
        }
    }

    @Override
    public void getFormattedTasksAndHabits(LoggedInViewModel loggedInViewModel, String taskSort, String habitSort) {
        try {
            ArrayList<Task> taskList = this.taskDataAccess.fetchTasks(loggedInViewModel.getState().getUsername());
            ArrayList<Habit> habitList = this.habitDataAccess.fetchHabits(loggedInViewModel.getState().getUsername());

            // Apply task sorting
            if (taskSort != null) {
                // Copy Task list to an ArrayList<Completable> for strategy sorting
                ArrayList<entities.Completable> taskCompletableList = new ArrayList<>();
                for (Task t : taskList) taskCompletableList.add(t);
                switch (taskSort) {
                    case "Priority":
                        new strategy.SortByPriorityStrategy().sort(taskCompletableList);
                        break;
                    case "Deadline":
                        new strategy.SortByDeadlineStrategy().sort(taskCompletableList);
                        break;
                    default:
                        // None
                }
                // Restore sorted order into taskList
                taskList = new ArrayList<>();
                for (entities.Completable c : taskCompletableList) taskList.add((Task) c);
            }

            // Apply habit sorting
            if (habitSort != null) {
                ArrayList<entities.Completable> habitCompletableList = new ArrayList<>();
                for (Habit h : habitList) habitCompletableList.add(h);
                switch (habitSort) {
                    case "Priority":
                        new strategy.SortByPriorityStrategy().sort(habitCompletableList);
                        break;
                    case "StartTime":
                        new strategy.SortByStartTimeStrategy().sort(habitCompletableList);
                        break;
                    default:
                        // None
                }
                // Restore sorted order into habitList
                habitList = new ArrayList<>();
                for (entities.Completable c : habitCompletableList) habitList.add((Habit) c);
            }

            // Format lists (reuse existing logic) -- we'll call the existing getFormattedTasksAndHabits by building formatted lists directly here
            ArrayList<ArrayList<String>> formattedTasks = new ArrayList<>();
            for (Task task : taskList) {
                ArrayList<String> formattedTask = new ArrayList<>();
                String taskName = task.getName();
                java.time.LocalDateTime taskDeadline = task.getDueDate();
                java.time.LocalDateTime taskStartTime = task.getStartTime();
                String taskGroup = task.getTaskGroup();
                boolean status = task.getStatus();
                int priority = task.getPriority();
                String description = task.getDescription();
                formattedTask.add(taskName);
                final java.time.format.DateTimeFormatter OUTPUT_FORMAT = java.time.format.DateTimeFormatter.ofPattern("dd MMMM, yyyy HH:mm", java.util.Locale.ENGLISH);
                formattedTask.add(taskStartTime == null ? "" : taskStartTime.format(OUTPUT_FORMAT));
                if (taskDeadline == null) {
                    formattedTask.add("");
                } else {
                    String taskDeadlineToString = taskDeadline.toString();
                    switch (taskDeadlineToString.substring(5, 7)) {
                        case "01":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " January, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "02":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " February, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "03":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " March, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "04":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " April, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "05":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " May, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "06":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " June, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "07":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " July, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "08":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " August, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "09":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " September, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "10":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " October, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "11":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " November, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                        case "12":
                            formattedTask.add(taskDeadlineToString.substring(8, 10) + " December, " + taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                            break;
                    }
                }
                formattedTask.add(taskGroup);
                formattedTask.add(status ? "Complete" : "Incomplete");
                formattedTask.add(Integer.toString(priority));
                formattedTask.add(description);
                formattedTasks.add(formattedTask);
            }

            ArrayList<ArrayList<String>> formattedHabits = new ArrayList<>();
            for (Habit habit : habitList) {
                ArrayList<String> formattedHabit = new ArrayList<>();
                formattedHabit.add(habit.getName());
                formattedHabit.add(habit.getStartTime() == null ? "" : habit.getStartTime().toString());
                formattedHabit.add(Integer.toString(habit.getFrequency()));
                formattedHabit.add(habit.getHabitGroup());
                formattedHabit.add(Integer.toString(habit.getStreakCount()));
                formattedHabit.add(Integer.toString(habit.getPriority()));
                formattedHabit.add(habit.getStatus() ? "Complete" : "Incomplete");
                formattedHabit.add(habit.getDescription());
                formattedHabits.add(formattedHabit);
            }

            ViewTasksAndHabitsOutputData outputData = new ViewTasksAndHabitsOutputData(formattedTasks, formattedHabits);
            presenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            presenter.prepareFailView("Failed to load task and habit data");
        }
    }

}
