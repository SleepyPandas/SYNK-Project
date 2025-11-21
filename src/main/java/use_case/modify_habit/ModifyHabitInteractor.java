package use_case.modify_habit;

import entities.Habit;
import entities.HabitBuilder;

import java.time.LocalDateTime;

public class ModifyHabitInteractor implements ModifyHabitInputBoundary {
    private final ModifyHabitOutputBoundary modifyHabitPresenter;
    private final ModifyHabitUserDataAccessInterface userDataAccessObject;

    public ModifyHabitInteractor(ModifyHabitOutputBoundary modifyHabitPresenter, ModifyHabitUserDataAccessInterface userDataAccessObject) {
        this.modifyHabitPresenter = modifyHabitPresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(ModifyHabitInputData modifyHabitInputData) {
        String userID = modifyHabitInputData.getUserID();
        String newHabitName = modifyHabitInputData.getNewHabitName();
        int newHabitPriority = modifyHabitInputData.getNewPriority();
        boolean newHabitStatus = modifyHabitInputData.getNewHabitStatus();
        LocalDateTime newStartDateTime = modifyHabitInputData.getNewStartDateTime();
        int newStreakCount = modifyHabitInputData.getNewStreakCount();
        String newHabitGroup = modifyHabitInputData.getNewHabitGroup();
        LocalDateTime frequency = modifyHabitInputData.getFrequency();

        final Habit modifiedHabit = new HabitBuilder()
                .setHabitName(newHabitName)
                .setPriority(newHabitPriority)
                .setStatus(newHabitStatus)
                .setStartDateTime(newStartDateTime)
                .setStreakCount(newStreakCount)
                .setHabitGroup(newHabitGroup)
                .setFrequency(frequency)
                .build();
        
        userDataAccessObject.saveHabit(userID, modifiedHabit);

        final ModifyHabitOutputData outputData = new ModifyHabitOutputData(
                newHabitName,
                newStartDateTime,
                frequency,
                newHabitGroup,
                newHabitPriority,
                newHabitStatus,
                newStreakCount
        );
        modifyHabitPresenter.prepareSuccessView(outputData);

    }

    @Override
    public void switchToHabitListView() {
        modifyHabitPresenter.switchToHabitListView();
    }
}

