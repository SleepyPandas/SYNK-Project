package use_case.modify_habit;

import entities.Habit;

public interface ModifyHabitUserDataAccessInterface {
    /***
     * Saves the modified task to the system under the given user
     * @param userID
     * @param modifiedHabit
     */
    void saveHabit(String userID, Habit modifiedHabit);


}
