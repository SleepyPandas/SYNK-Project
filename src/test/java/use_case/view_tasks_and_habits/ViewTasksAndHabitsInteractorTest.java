package use_case.view_tasks_and_habits;

import data_access.InMemoryHabitDataAccessObject;
import data_access.InMemoryTaskDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entities.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsState;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsViewModel;
import use_case.gateways.HabitGateway;
import use_case.gateways.TaskGateway;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsPresenter;
import use_case.gateways.UserGateway;
import use_case.view_stats.ViewStatsOutputData;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ViewTasksAndHabitsInteractorTest {

    void SuccessTest() {

        TaskGateway taskGateway = new InMemoryTaskDataAccessObject();
        HabitGateway habitGateway = new InMemoryHabitDataAccessObject();
        UserGateway userGateway = new InMemoryUserDataAccessObject();

        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        ViewTasksAndHabitsViewModel correctViewModel = new  ViewTasksAndHabitsViewModel();
        ViewTasksAndHabitsViewModel testViewModel = new  ViewTasksAndHabitsViewModel();
        ViewManagerModel viewManagerModel = new  ViewManagerModel();
        ViewTasksAndHabitsPresenter correctPresenter = new ViewTasksAndHabitsPresenter(viewManagerModel, correctViewModel);
        ViewTasksAndHabitsPresenter testPresenter = new ViewTasksAndHabitsPresenter(viewManagerModel, testViewModel);

        TaskBuilder taskBuilder = new TaskBuilder();
        HabitBuilder habitBuilder = new HabitBuilder();

        Task task = taskBuilder
                .setTaskGroup("Problem set")
                .setTaskName("Problem set")
                .setStartTime(LocalDateTime.parse( "2025-11-26T00:00:00"))
                .setDeadline(LocalDateTime.parse( "2025-11-27T00:00:00"))
                .setStatus(true)
                .setPriority(2)
                .setDescription("Finishing a problem set")
                .build();

        Task task2 = taskBuilder
                .setTaskGroup("project")
                .setTaskName("project")
                .setStartTime(LocalDateTime.parse( "2025-11-26T00:00:00"))
                .setDeadline(LocalDateTime.parse( "2025-11-27T00:00:00"))
                .setStatus(false)
                .setPriority(2)
                .setDescription("Finishing a project")
                .build();

        Habit habit = habitBuilder.setHabitName("Not programming")
                .setHabitGroup("Programming")
                .setFrequency(1)
                .setStartDateTime(LocalDateTime.parse( "2025-11-27T00:00:00"))
                .setPriority(1)
                .setStatus(true)
                .setStreakCount(1)
                .setDescription("Not doing some programming")
                .build();
        Habit habit2 = habitBuilder.setHabitName("Programming")
                .setHabitGroup("Programming")
                .setFrequency(1)
                .setStartDateTime(LocalDateTime.parse( "2025-11-27T00:00:00"))
                .setPriority(4)
                .setStatus(false)
                .setStreakCount(10)
                .setDescription("Doing some programming")
                .build();

        UserFactory userFactory = new UserFactory();

        User arya = userFactory.create("arya", "123");
        userGateway.save(arya);

        taskGateway.addTask("arya", task);
        taskGateway.addTask("arya", task2);
        habitGateway.addHabit("arya", habit);
        habitGateway.addHabit("arya", habit2);

        ViewTasksAndHabitsInteractor viewTasksAndHabitsInteractor = new ViewTasksAndHabitsInteractor(taskGateway,
                habitGateway, testPresenter);

        ArrayList<String> formattedTask = new ArrayList<>();
        formattedTask.add("Problem Set");
        formattedTask.add("26 November 25, 00:00");
        formattedTask.add("27 November 25, 00:00");
        formattedTask.add("Problem Set");
        formattedTask.add("Incomplete");
        formattedTask.add("2");
        formattedTask.add("Finishing a problem set");

        ArrayList<String> formattedTask2 = new ArrayList<>();
        formattedTask.add("project");
        formattedTask.add("26 November 25, 00:00");
        formattedTask.add("27 November 25, 00:00");
        formattedTask.add("project");
        formattedTask.add("Incomplete");
        formattedTask.add("2");
        formattedTask.add("Finishing a project");

        ArrayList<String> formattedHabit = new ArrayList<>();
        formattedHabit.add("Not programming");
        formattedHabit.add("27 November 25, 00:00");
        formattedHabit.add("1");
        formattedHabit.add("Programming");
        formattedHabit.add("1");
        formattedHabit.add("Not doing some programming");

        ArrayList<String> formattedHabit2 = new ArrayList<>();
        formattedTask.add("Programming");
        formattedTask.add("27 November 25, 00:00");
        formattedTask.add("1");
        formattedTask.add("Programming");
        formattedTask.add("1");
        formattedTask.add("Doing some  programming");

        ArrayList<ArrayList<String>> formattedTasks = new ArrayList<>();
        formattedTasks.add(formattedTask);
        formattedTasks.add(formattedTask2);

        ArrayList<ArrayList<String>> formattedHabits = new ArrayList<>();
        formattedHabits.add(formattedHabit);
        formattedHabits.add(formattedHabit2);

        ViewTasksAndHabitsOutputData correctOutputData = new ViewTasksAndHabitsOutputData(formattedTasks, formattedHabits);
        correctPresenter.prepareSuccessView(correctOutputData);

        viewTasksAndHabitsInteractor.getFormattedTasksAndHabits(loggedInViewModel);


    }
}
