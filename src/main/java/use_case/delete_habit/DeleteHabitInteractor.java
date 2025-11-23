package use_case.delete_habit;

public class DeleteHabitInteractor implements DeleteHabitInputBoundary {

    private final DeleteHabitOutputBoundary presenter;
    private final DeleteHabitUserDataAccess dao;

    public DeleteHabitInteractor(DeleteHabitOutputBoundary presenter,
                                 DeleteHabitUserDataAccess dao) {
        this.presenter = presenter;
        this.dao = dao;
    }

    @Override
    public void execute(DeleteHabitInputData deleteHabitInputData) {
        String habitName = deleteHabitInputData.getHabitName();

        if (habitName == null || habitName.trim().isEmpty()) {
            presenter.prepareFailView("Habit name cannot be empty.");
            return;
        }

        if (!dao.existsByName(habitName)) {
            presenter.prepareFailView("Habit '" + habitName + "' does not exist.");
            return;
        }

        try {
            dao.deleteHabit(habitName);

            DeleteHabitOutputData outputData =
                    new DeleteHabitOutputData(habitName, false);

            presenter.prepareSuccessView(outputData);

        } catch (Exception exception) {
            presenter.prepareFailView("Failed to delete habit: " + exception.getMessage());
        }
    }
}
