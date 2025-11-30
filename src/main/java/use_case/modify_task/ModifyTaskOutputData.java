package use_case.modify_task;

import entities.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ModifyTaskOutputData{

    private ArrayList<Task> taskList;

    public ModifyTaskOutputData(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<ArrayList<String>> getTaskList() {
        ArrayList<ArrayList<String>> formattedTasks = new ArrayList<>();

        for (Task task : taskList) {
            ArrayList<String> formattedTask = new ArrayList<>();

            String taskName = task.getName();
            LocalDateTime taskDeadline = task.getDeadline();
            String taskGroup = task.getTaskGroup();
            boolean status = task.getStatus();
            int priority = task.getPriority();
            String description = task.getDescription();

            formattedTask.add(taskName);

            String taskDeadlineToString = taskDeadline.toString();
            switch (taskDeadlineToString.substring(5, 7)) {
                case "01":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " January, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "02":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " February, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "03":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " March, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "04":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " April, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "05":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " May, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "06":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " June, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "07":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " July, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "08":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " August, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "09":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " September, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "10":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " October, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "11":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " November, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
                case "12":
                    formattedTask.add(taskDeadlineToString.substring(8, 10) + " December, " +
                            taskDeadlineToString.substring(0, 4) + " " + taskDeadlineToString.substring(11, 16));
                    break;
            }

            formattedTask.add(taskGroup);

            if (status == true) {
                formattedTask.add("Complete");
            } else {
                formattedTask.add("Incomplete");
            }

            formattedTask.add(Integer.toString(priority));

            formattedTask.add(description);

            formattedTasks.add(formattedTask);

        }
        return formattedTasks;
    }
}
