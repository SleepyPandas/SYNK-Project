package use_case.modify_habit;

import java.time.LocalDateTime;

public class ModifyHabitInputData {
    private final String userID;
    private final String newHabitName;
    private final int newPriority;
    private final LocalDateTime newStartDateTime;
    private final Boolean newHabitStatus;
    private final int newStreakCount;
    private final String newHabitGroup;
    private final LocalDateTime frequency;

    public ModifyHabitInputData(String newHabitName, int newPriority, Boolean newHabitStatus, String userID, LocalDateTime newStartDateTime, int newStreakCount, String newHabitGroup, LocalDateTime frequency) {
        this.newHabitName = newHabitName;
        this.newPriority = newPriority;
        this.newHabitStatus = newHabitStatus;
        this.userID = userID;
        this.newStartDateTime = newStartDateTime;
        this.newStreakCount = newStreakCount;
        this.newHabitGroup = newHabitGroup;
        this.frequency = frequency;
    }

    public int getNewPriority() {
        return newPriority;
    }

    public String getNewHabitName() {
        return newHabitName;
    }

    public Boolean getNewHabitStatus() {
        return newHabitStatus;
    }

    public String getUserID() {
        return this.userID;
    }

    public int getNewStreakCount() {
        return newStreakCount;
    }

    public String getNewHabitGroup() {
        return newHabitGroup;
    }

    public LocalDateTime getFrequency() {
        return frequency;
    }

    public LocalDateTime getNewStartDateTime() {
        return newStartDateTime;
    }
}
