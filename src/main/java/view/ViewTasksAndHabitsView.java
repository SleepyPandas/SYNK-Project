package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsController;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsState;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ViewTasksAndHabitsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ViewTasksAndHabitsViewModel viewTasksAndHabitsViewModel;
    private ViewTasksAndHabitsController viewTasksAndHabitsController;
    private final String viewName = "view tasks and habits";
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;

    // Controllers for CRUD operations
    private interface_adapter.create_habit.CreateHabitController createHabitController;
    private interface_adapter.delete_habit.DeleteHabitController deleteHabitController;
    private interface_adapter.create_task.CreateTaskController createTaskController;
    private interface_adapter.delete_task.DeleteTaskController deleteTaskController;

    private DefaultTableModel taskModel;
    private DefaultTableModel habitModel;

    private final JButton refreshButton;
    private final JButton exitButton;
    private final JButton createTaskButton;
    private final JButton deleteTaskButton;
    private final JButton createHabitButton;
    private final JButton deleteHabitButton;

    public ViewTasksAndHabitsView(ViewTasksAndHabitsViewModel viewTasksAndHabitsViewModel,
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {

        this.viewTasksAndHabitsViewModel = viewTasksAndHabitsViewModel;
        this.viewTasksAndHabitsViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;

        this.taskModel = new DefaultTableModel(viewTasksAndHabitsViewModel.taskCols, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        this.habitModel = new DefaultTableModel(viewTasksAndHabitsViewModel.habitCols, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        final JPanel TablePanel = new JPanel();
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));

        this.exitButton = new JButton("Exit");
        this.refreshButton = new JButton("Refresh");
        this.createTaskButton = new JButton("Create Task");
        this.deleteTaskButton = new JButton("Delete Task");
        this.createHabitButton = new JButton("Create Habit");
        this.deleteHabitButton = new JButton("Delete Habit");

        buttonPanel.add(this.createTaskButton);
        buttonPanel.add(this.deleteTaskButton);
        buttonPanel.add(this.createHabitButton);
        buttonPanel.add(this.deleteHabitButton);
        buttonPanel.add(this.refreshButton);
        buttonPanel.add(this.exitButton);

        final JLabel TableLabel = new JLabel("Tasks and Habits");

        setLayout(new BorderLayout());

        JTable taskTable = new JTable(this.taskModel);
        taskTable.setFillsViewportHeight(true);
        JScrollPane taskScrollPane = new JScrollPane(taskTable);

        JTable habitTable = new JTable(this.habitModel);
        habitTable.setFillsViewportHeight(true);
        JScrollPane habitScrollPane = new JScrollPane(habitTable);

        add(TablePanel, BorderLayout.SOUTH);
        add(TableLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1200, 500));
        tabbedPane.addTab("Tasks", taskScrollPane);
        tabbedPane.addTab("Habits", habitScrollPane);

        add(tabbedPane, BorderLayout.CENTER);

        this.exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(exitButton)) {
                    viewManagerModel.setState(loggedInViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        this.refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(refreshButton)) {

                    if (viewTasksAndHabitsController != null) {
                        viewTasksAndHabitsController.getFormattedTasksAndHabits(loggedInViewModel);
                    } else {
                        JOptionPane.showMessageDialog(ViewTasksAndHabitsView.this,
                                "Initialization in progress. Please wait a moment.",
                                "Loading", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        // Create Task button handler
        this.createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(createTaskButton)) {
                    handleCreateTask();
                }
            }
        });

        // Delete Task button handler
        this.deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(deleteTaskButton)) {
                    handleDeleteTask();
                }
            }
        });

        // Create Habit button handler
        this.createHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(createHabitButton)) {
                    handleCreateHabit();
                }
            }
        });

        // Delete Habit button handler
        this.deleteHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(deleteHabitButton)) {
                    handleDeleteHabit();
                }
            }
        });

        if (this.viewTasksAndHabitsController != null && this.loggedInViewModel != null) {
            this.viewTasksAndHabitsController.getFormattedTasksAndHabits(this.loggedInViewModel);
        }

    }

    /**
     * Handle Create Task button click - shows dialog to input task details
     */
    private void handleCreateTask() {
        if (createTaskController == null) {
            JOptionPane.showMessageDialog(this,
                    "Create Task controller not initialized.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = loggedInViewModel.getState().getUsername();

        JTextField taskNameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField deadlineField = new JTextField();
        JTextField groupField = new JTextField();
        JTextField priorityField = new JTextField();

        Object[] message = {
                "Task Name:", taskNameField,
                "Description:", descriptionField,
                "Deadline (yyyy-MM-ddTHH:mm):", deadlineField,
                "Group:", groupField,
                "Priority (integer):", priorityField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Create Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String taskName = taskNameField.getText().trim();
                String description = descriptionField.getText().trim();
                LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText().trim());
                String group = groupField.getText().trim();
                int priority = Integer.parseInt(priorityField.getText().trim());

                createTaskController.execute(username, taskName, description, deadline, group, false, priority);

                // Refresh the view after creating
                if (viewTasksAndHabitsController != null) {
                    viewTasksAndHabitsController.getFormattedTasksAndHabits(loggedInViewModel);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error creating task: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Handle Delete Task button click - shows dialog to input task name
     */
    private void handleDeleteTask() {
        if (deleteTaskController == null) {
            JOptionPane.showMessageDialog(this,
                    "Delete Task controller not initialized.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = loggedInViewModel.getState().getUsername();
        String taskName = JOptionPane.showInputDialog(this, "Enter task name to delete:");

        if (taskName != null && !taskName.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete task '" + taskName + "'?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    deleteTaskController.execute(username, taskName.trim());

                    // Refresh the view after deleting
                    if (viewTasksAndHabitsController != null) {
                        viewTasksAndHabitsController.getFormattedTasksAndHabits(loggedInViewModel);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Error deleting task: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Handle Create Habit button click - shows dialog to input habit details
     */
    private void handleCreateHabit() {
        if (createHabitController == null) {
            JOptionPane.showMessageDialog(this,
                    "Create Habit controller not initialized.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = loggedInViewModel.getState().getUsername();

        JTextField habitNameField = new JTextField();
        JTextField startDateTimeField = new JTextField();
        JTextField frequencyField = new JTextField();
        JTextField habitGroupField = new JTextField();
        JTextField streakCountField = new JTextField("0");
        JTextField priorityField = new JTextField();

        Object[] message = {
                "Habit Name:", habitNameField,
                "Start Date & Time (yyyy-MM-ddTHH:mm):", startDateTimeField,
                "Frequency (days):", frequencyField,
                "Habit Group:", habitGroupField,
                "Streak Count:", streakCountField,
                "Priority (integer):", priorityField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Create Habit", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String habitName = habitNameField.getText().trim();
                String startDateTimeText = startDateTimeField.getText().trim();
                String frequencyText = frequencyField.getText().trim();
                String habitGroup = habitGroupField.getText().trim();
                int streakCount = Integer.parseInt(streakCountField.getText().trim());
                int priority = Integer.parseInt(priorityField.getText().trim());

                createHabitController.execute(username, habitName, startDateTimeText,
                        frequencyText, habitGroup, streakCount, priority);

                // Refresh the view after creating
                if (viewTasksAndHabitsController != null) {
                    viewTasksAndHabitsController.getFormattedTasksAndHabits(loggedInViewModel);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error creating habit: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Handle Delete Habit button click - shows dialog to input habit name
     */
    private void handleDeleteHabit() {
        if (deleteHabitController == null) {
            JOptionPane.showMessageDialog(this,
                    "Delete Habit controller not initialized.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String username = loggedInViewModel.getState().getUsername();
        String habitName = JOptionPane.showInputDialog(this, "Enter habit name to delete:");

        if (habitName != null && !habitName.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete habit '" + habitName + "'?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    deleteHabitController.execute(username, habitName.trim());

                    // Refresh the view after deleting
                    if (viewTasksAndHabitsController != null) {
                        viewTasksAndHabitsController.getFormattedTasksAndHabits(loggedInViewModel);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Error deleting habit: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Required method for ActionListener, though typically handled via anonymous
     * classes now.
     * 
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        // Empty
    }

    /**
     * Clears and updates the JTables with fresh data from the ViewModel.
     */
    public void updateTable(ArrayList<ArrayList<String>> taskList, ArrayList<ArrayList<String>> habitList) {

        taskModel.setRowCount(0);
        habitModel.setRowCount(0);

        for (ArrayList<String> row : taskList) {
            Object[] rowData = row.toArray();
            taskModel.addRow(rowData);
        }

        for (ArrayList<String> row : habitList) {
            Object[] rowData = row.toArray();
            habitModel.addRow(rowData);
        }
    }

    /**
     * Setter for Dependency Injection, used by the main application builder to wire
     * up the controller later.
     */
    public void setViewTasksAndHabitsController(ViewTasksAndHabitsController viewTasksAndHabitsController) {
        this.viewTasksAndHabitsController = viewTasksAndHabitsController;
        if (this.loggedInViewModel != null) {
            this.viewTasksAndHabitsController.getFormattedTasksAndHabits(this.loggedInViewModel);
        }
    }

    /**
     * Setters for CRUD controllers - for dependency injection
     */
    public void setCreateHabitController(interface_adapter.create_habit.CreateHabitController createHabitController) {
        this.createHabitController = createHabitController;
    }

    public void setDeleteHabitController(interface_adapter.delete_habit.DeleteHabitController deleteHabitController) {
        this.deleteHabitController = deleteHabitController;
    }

    public void setCreateTaskController(interface_adapter.create_task.CreateTaskController createTaskController) {
        this.createTaskController = createTaskController;
    }

    public void setDeleteTaskController(interface_adapter.delete_task.DeleteTaskController deleteTaskController) {
        this.deleteTaskController = deleteTaskController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Reacts to changes in the ViewModel state. This is how the table data is
     * refreshed after a controller call.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            ViewTasksAndHabitsState state = (ViewTasksAndHabitsState) viewTasksAndHabitsViewModel.getState();

            if (state.getErrorMessage() != null && !state.getErrorMessage().isEmpty()) {
                JOptionPane.showMessageDialog(this, state.getErrorMessage(), "Data Loading Error",
                        JOptionPane.ERROR_MESSAGE);
                state.setErrorMessage(null);
            }

            ArrayList<ArrayList<String>> formattedTasks = state.getFormattedTasks();
            ArrayList<ArrayList<String>> formattedHabits = state.getFormattedHabits();
            updateTable(formattedTasks, formattedHabits);
        }
    }
}