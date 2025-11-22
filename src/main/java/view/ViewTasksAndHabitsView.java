package view;

import interface_adapter.login.LoginController;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsController;
import interface_adapter.view_tasks_and_habits.ViewTasksAndHabitsViewModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class ViewTasksAndHabitsView extends JPanel {

    private final ViewTasksAndHabitsViewModel viewTasksAndHabitsViewModel;
    private final String viewName = "view tasks and habits";
    private ViewTasksAndHabitsController viewTasksAndHabitsController = null;


    public ViewTasksAndHabitsView(ViewTasksAndHabitsViewModel viewTasksAndHabitsViewModel) {
        this.viewTasksAndHabitsViewModel = viewTasksAndHabitsViewModel;

        final JPanel mainPanel = new JPanel();
        final JPanel taskTablePanel = new JPanel();
        final JPanel habitTablePanel = new JPanel();

        final JButton

        mainPanel.add(taskTablePanel);
        mainPanel.add(habitTablePanel);

        final JLabel taskTableLabel = new JLabel("Task Table");
        final JLabel habitTableLabel = new JLabel("Habit Table");

        final DefaultTableModel taskModel =  new DefaultTableModel(viewTasksAndHabitsViewModel.taskCols, 0);
        final DefaultTableModel habitModel =  new DefaultTableModel(viewTasksAndHabitsViewModel.habitCols, 0);

        JTable taskTable = new JTable(taskModel);
        JTable habitTable = new JTable(habitModel);

        taskModel.addRow(ViewTasksAndHabitsViewModel.sampleTask);
        habitModel.addRow(ViewTasksAndHabitsViewModel.sampleHabit);

        taskTablePanel.add(taskTableLabel);
        taskTablePanel.add(taskTable);
        habitTablePanel.add(habitTableLabel);
        habitTablePanel.add(habitTable);

        this.add(mainPanel);

        taskModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {

                int row = e.getFirstRow();
                int col = e.getColumn();

                String taskName = taskModel.getValueAt(row, 0).toString();

                viewTasksAndHabitsController.execute("task", col, taskName);

            }
        });

        habitModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                String habitName = habitModel.getValueAt(row, 0).toString();

                viewTasksAndHabitsController.execute("habit", col, habitName);

            }
        });
    }

    public void setViewTasksAndHabitsController(ViewTasksAndHabitsController viewTasksAndHabitsController) {
        this.viewTasksAndHabitsController = viewTasksAndHabitsController;
    }

    public String getViewName() { return viewName; }
}
