package interface_adapter.modify_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.modify_task.ModifyTaskOutputBoundary;
import use_case.modify_task.ModifyTaskOutputData;

public class ModifyHabitPresenter implements ModifyTaskOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private ModifyHabitViewModel modifyHabitViewModel;
    private LoginViewModel loginViewModel;
    // TODO add Arya's view


    // TODO add Arya's use case's view model when ready
    public ModifyHabitPresenter(ViewManagerModel viewManagerModel,
                                ModifyHabitViewModel modifyHabitViewModel,
                                LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.modifyHabitViewModel = modifyHabitViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(ModifyTaskOutputData outputData) {
        // on success, switch to task list view
        // TODO switch to Arya's use case's view when ready
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    public void switchToTaskListView(){
        // TODO Arya's use case's view
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
