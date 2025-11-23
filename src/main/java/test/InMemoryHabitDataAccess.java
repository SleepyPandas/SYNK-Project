package test;


import entities.Habit;
import use_case.create_habit.CreateHabitUserDataAccess;

import java.util.HashMap;
import java.util.Map;

public class InMemoryHabitDataAccess implements CreateHabitUserDataAccess {

    // username -> (habitName -> Habit)
    private final Map<String, Map<String, Habit>> storage = new HashMap<>();

    @Override
    public void save(String username, Habit habit) {
        storage.computeIfAbsent(username, k -> new HashMap<>())
                .put(habit.getHabitName(), habit);
        System.out.println("[DAO] Saved habit '" + habit.getHabitName() + "' for user " + username);
    }

    @Override
    public boolean existsByName(String username, String habitName) {
        return storage.containsKey(username)
                && storage.get(username).containsKey(habitName);
    }
}
