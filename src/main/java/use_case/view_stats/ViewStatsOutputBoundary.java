package use_case.view_stats;

public interface ViewStatsOutputBoundary {

    void prepareSuccessView(ViewStatsOutputData outputData);

    void switchToTaskList();
}
