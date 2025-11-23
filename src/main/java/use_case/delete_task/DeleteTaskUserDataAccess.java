package use_case.delete_task;

public interface DeleteTaskUserDataAccess {

    void deleteTask(String taskName);

    boolean existsTaskByName(String taskName);
}
