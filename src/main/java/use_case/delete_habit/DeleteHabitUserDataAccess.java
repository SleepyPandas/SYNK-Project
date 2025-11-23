package use_case.delete_habit;

public interface DeleteHabitUserDataAccess {
    void  deleteHabit(String habit);
    boolean existsByName(String habitName);
}
