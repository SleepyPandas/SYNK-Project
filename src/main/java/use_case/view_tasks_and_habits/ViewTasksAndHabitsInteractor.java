package use_case.view_tasks_and_habits;


import data_access.FileUserDataAccessObject;

public class ViewTasksAndHabitsInteractor implements ViewTasksAndHabitsInputBoundary {
    private FileUserDataAccessObject userDataAccess;
    private ViewTasksAndHabitsOutputBoundary presenter;

    public ViewTasksAndHabitsInteractor(FileUserDataAccessObject userDataAccess, ViewTasksAndHabitsOutputBoundary presenter){
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }


    @Override
    public void execute(ViewTasksAndHabitsInputData InputData) {

    }
}
