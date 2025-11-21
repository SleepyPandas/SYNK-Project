package use_case.modify_habit;

public interface ModifyHabitInputBoundary {

    /**
     * Executes the modify task use case
     * @param modifyTaskInputData the input data
     */
    void execute(ModifyHabitInputData modifyTaskInputData);

    void switchToHabitListView();
}
