package use_case.modify_habit;

import entities.Task;

public interface ModifyHabitUserDataAccessInterface {
    /***
     * Saves the modified task to the system under the given user
     * @param userID
     * @param modifiedTask
     */
    void saveTask(String userID, Task modifiedTask);


}
