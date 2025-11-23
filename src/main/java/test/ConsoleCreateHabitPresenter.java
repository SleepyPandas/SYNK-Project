package test;
import interface_adapter.create_habit.CreateHabitState;
import interface_adapter.create_habit.CreateHabitViewModel;
import use_case.create_habit.CreateHabitOutputBoundary;
import use_case.create_habit.CreateHabitOutputData;

import java.time.format.DateTimeFormatter;

public class ConsoleCreateHabitPresenter implements CreateHabitOutputBoundary {

    private final CreateHabitViewModel viewModel;

    public ConsoleCreateHabitPresenter(CreateHabitViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(CreateHabitOutputData outputData) {
        CreateHabitState state = viewModel.getState();

        state.setHabitName(outputData.getHabitName());
        state.setStartDateTimeText(
                outputData.getStartDateTime()
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        state.setErrorMessage(null);
        state.setSuccessMessage("Created habit: " + outputData.getHabitName());

        viewModel.setState(state);
        viewModel.firePropertyChanged();

        System.out.println("=== SUCCESS VIEW ===");
        printState(state);
    }

    @Override
    public void prepareFailView(String errorMessage) {
        CreateHabitState state = viewModel.getState();
        state.setErrorMessage(errorMessage);
        state.setSuccessMessage(null);

        viewModel.setState(state);
        viewModel.firePropertyChanged();

        System.out.println("=== FAIL VIEW ===");
        printState(state);
    }

    private void printState(CreateHabitState state) {
        System.out.println("habitName        = " + state.getHabitName());
        System.out.println("startDateTimeTxt = " + state.getStartDateTimeText());
        System.out.println("errorMessage     = " + state.getErrorMessage());
        System.out.println("successMessage   = " + state.getSuccessMessage());
        System.out.println("-----------------------------");
    }
}
