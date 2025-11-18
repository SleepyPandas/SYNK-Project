package use_case.view_tasks_and_habits;
import java.time.LocalDateTime;
/**
 * The Input Data for the CreateTask Use Case.
 */
public class ViewTasksAndHabitsInputData {

    private final String designation;
    private final int col;
    private final String completeableName;

    public ViewTasksAndHabitsInputData(String designation, int col, String completeableName) {
        this.designation = designation;
        this.col = col;
        this.completeableName = completeableName;
    }

    String getDesignation() {return designation;}
    int getCol() {return col;}
    String getCompleteableName() {return completeableName;}


}
