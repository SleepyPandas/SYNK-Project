package interface_adapter.modify_task;

import use_case.modify_task.ModifyTaskOutputBoundary;
import use_case.modify_task.ModifyTaskOutputData;

public class ModifyTaskPresenter implements ModifyTaskOutputBoundary {

    @Override
    public void prepareSuccessView(ModifyTaskOutputData outputData) {
        // on success, switch to task list view
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
