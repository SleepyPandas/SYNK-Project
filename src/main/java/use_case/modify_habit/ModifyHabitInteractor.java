package use_case.modify_habit;

import entities.Habit;
import entities.HabitBuilder;
import use_case.gateways.HabitGateway;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale; // Imported Locale

public class ModifyHabitInteractor implements ModifyHabitInputBoundary {
    private final ModifyHabitOutputBoundary modifyHabitPresenter;
    private final HabitGateway habitDataAccessObject;

    // Define the CUSTOM_FORMATTER as a static field for the new habit data (from UI)
    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM, yyyy HH:mm", Locale.ENGLISH);

    public ModifyHabitInteractor(ModifyHabitOutputBoundary modifyHabitPresenter, HabitGateway habitDataAccessObject) {
        this.modifyHabitPresenter = modifyHabitPresenter;
        this.habitDataAccessObject = habitDataAccessObject;
    }

    public void execute(ModifyHabitInputData modifyHabitInputData) {
        String userID = modifyHabitInputData.getUserID();

        final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMMM, yyyy HH:mm", Locale.ENGLISH);


        final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String oldHabitName = modifyHabitInputData.getOldHabitName();
        String oldPriority = modifyHabitInputData.getOldPriority();
        boolean oldHabitStatus = modifyHabitInputData.getOldHabitStatus();
        String oldStartDateTime = modifyHabitInputData.getOldStartDateTime();
        String oldStreakCount = modifyHabitInputData.getOldStreakCount();
        String oldHabitGroup = modifyHabitInputData.getOldHabitGroup();
        String oldFrequency = modifyHabitInputData.getOldFrequency();


        String newHabitName = modifyHabitInputData.getNewHabitName();
        String newPriority = modifyHabitInputData.getNewPriority();
        boolean newHabitStatus = modifyHabitInputData.getNewHabitStatus();
        String newStartDateTime = modifyHabitInputData.getNewStartDateTime();
        String newStreakCount = modifyHabitInputData.getNewStreakCount();
        String newHabitGroup = modifyHabitInputData.getNewHabitGroup();
        String newFrequency = modifyHabitInputData.getNewFrequency();

        try {

            LocalDateTime newDateTimeObject = LocalDateTime.parse(newStartDateTime, INPUT_FORMAT);
            String newDateTimeFormatted = newDateTimeObject.format(OUTPUT_FORMAT);

            LocalDateTime newStartDateTimeFormatted = LocalDateTime.parse(newDateTimeFormatted);
            int newFrequencyFormatted = Integer.parseInt(newFrequency);
            int newPriorityFormatted = Integer.parseInt(newPriority);
            int newStreakCountFormatted = Integer.parseInt(newStreakCount);

            LocalDateTime oldDateTimeObject = LocalDateTime.parse(oldStartDateTime, INPUT_FORMAT);
            String outputDateTime = oldDateTimeObject.format(OUTPUT_FORMAT);

            final Habit oldHabit = new HabitBuilder()
                    .setHabitName(oldHabitName)
                    .setPriority(Integer.parseInt(oldPriority))
                    .setStatus(oldHabitStatus)
                    .setStartDateTime(LocalDateTime.parse(outputDateTime))
                    .setStreakCount(Integer.parseInt(oldStreakCount))
                    .setHabitGroup(oldHabitGroup)
                    .setFrequency(Integer.parseInt(oldFrequency))
                    .build();

            final Habit modifiedHabit = oldHabit.clone();

            modifiedHabit.setHabitName(newHabitName);
            modifiedHabit.setPriority(newPriorityFormatted);
            modifiedHabit.setStatus(newHabitStatus);
            modifiedHabit.setStartDateTime(newStartDateTimeFormatted);
            modifiedHabit.setStreakCount(newStreakCountFormatted);
            modifiedHabit.setHabitGroup(newHabitGroup);
            modifiedHabit.setFrequency(newFrequencyFormatted);

            ArrayList<Habit> habitList = habitDataAccessObject.fetchHabits(userID);
            for (Habit habit : habitList) {
                if (!habit.equals(oldHabit) && habit.getName().equals(modifiedHabit.getName())){
                    modifyHabitPresenter.prepareFailView("Habit already exists");
                    return;
                }
            }

            habitDataAccessObject.deleteHabit(userID, oldHabit);
            habitDataAccessObject.addHabit(userID, modifiedHabit);

            modifyHabitPresenter.prepareSuccessView(new
                    ModifyHabitOutputData(habitDataAccessObject.fetchHabits(userID)));

        } catch (java.time.format.DateTimeParseException d) {
            modifyHabitPresenter.prepareFailView("Invalid Date/Time Format. Ensure new date matches 'dd MMMM, yyyy HH:mm' and old date matches 'yyyy-MM-ddTHH:mm:ss'.");
        } catch (NumberFormatException n) {
            modifyHabitPresenter.prepareFailView("Invalid Priority, Streak Count, or Frequency value (must be a number).");
        }
    }

    @Override
    public void switchToHabitListView() {
        modifyHabitPresenter.switchToHabitListView();
    }
}