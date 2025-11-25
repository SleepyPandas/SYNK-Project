package use_case.view_stats;

public interface ViewStatsInputBoundary {

    void execute(ViewStatsInputData inputData);

    void switchToTaskView();
}
