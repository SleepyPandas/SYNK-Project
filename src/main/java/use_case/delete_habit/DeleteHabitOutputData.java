package use_case.delete_habit;

public class DeleteHabitOutputData {

    private final String habitName;
    private final boolean useCaseFailed;

    public DeleteHabitOutputData(String habitName, boolean useCaseFailed) {
        this.habitName = habitName;
        this.useCaseFailed = useCaseFailed;
    }


    public String getHabitName() {
        return habitName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
