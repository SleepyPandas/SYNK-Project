package view;

import javax.swing.*;

import interface_adapter.modify_task.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class ModifyTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "modify task";
    private final ModifyTaskViewModel modifyTaskViewModel;

    private final JTextField newTaskName = new JTextField();
    private final JTextField newTaskDeadline = new JTextField();
    private final JRadioButton taskCompleted = new JRadioButton("Completed");
    private final JRadioButton taskNotCompleted = new JRadioButton("Not completed");
    private final ButtonGroup newTaskStatus = new ButtonGroup();
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance();
    private final JFormattedTextField newTaskPriority = new JFormattedTextField(numberFormat);

    private final JButton save = new JButton("save");

    public ModifyTaskView(ModifyTaskViewModel modifyTaskViewModel) {
        this.modifyTaskViewModel = modifyTaskViewModel;
        this.modifyTaskViewModel.addPropertyChangeListener(this);

        newTaskStatus.add(taskCompleted);
        newTaskStatus.add(taskNotCompleted);

        JLabel taskNameLabel = new JLabel("Task name");
        JLabel taskDeadlineLabel = new JLabel("Task deadline");
        JLabel taskStatusLabel = new JLabel("Task status");
        JLabel taskPriorityLabel = new JLabel("Task priority");

        JPanel taskStatus = new JPanel();
        JPanel taskPriority = new JPanel();
        JPanel taskName = new JPanel();
        JPanel taskDeadline = new JPanel();

        taskStatus.add(taskStatusLabel);
        taskStatus.add(taskCompleted);
        taskStatus.add(taskNotCompleted);
        taskStatus.setLayout(new BoxLayout(taskStatus, BoxLayout.X_AXIS));

        taskName.add(taskNameLabel);
        taskName.add(newTaskName);
        taskName.setLayout(new BoxLayout(taskName, BoxLayout.X_AXIS));

        taskDeadline.add(taskDeadlineLabel);
        taskDeadline.add(newTaskDeadline);
        taskDeadline.setLayout(new BoxLayout(taskDeadline, BoxLayout.X_AXIS));

        taskPriority.add(taskPriorityLabel);
        taskPriority.add(newTaskPriority);
        taskPriority.setLayout(new BoxLayout(taskPriority, BoxLayout.X_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(taskName);
        this.add(taskDeadline);
        this.add(taskStatus);
        this.add(taskPriority);
        this.add(save);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
