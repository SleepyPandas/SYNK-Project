package interface_adapter.view_stats;

import use_case.view_stats.ViewStatsInputBoundary;
import use_case.view_stats.ViewStatsInputData;

public class ViewStatsController {
    private final ViewStatsInputBoundary viewStatsInteractor;

    public ViewStatsController(ViewStatsInputBoundary viewStatsInteractor) {
        this.viewStatsInteractor = viewStatsInteractor;
    }

    public void execute(String userID){
        ViewStatsInputData inputData = new ViewStatsInputData(userID);

        this.viewStatsInteractor.execute(inputData);
    }

    public void switchToTaskView(){
        viewStatsInteractor.switchToTaskView();
    }
}
