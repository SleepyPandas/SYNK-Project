package use_case.modify_task;

import entities.Task;
import entities.User;

public interface ModifyTaskUserDataAccessInterface {
    /***
     * Saves the modified task to the system under the given user
     * @param userID
     * @param modifiedTask
     */
    public void saveTask(String userID, Task modifiedTask);


}
