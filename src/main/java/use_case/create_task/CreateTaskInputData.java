package use_case.create_task;
import java.time.LocalDateTime;
/**
 * The Input Data for the CreateTask Use Case.
 */
public class CreateTaskInputData {

    private String taskName;
    private LocalDateTime deadline;
    private String taskGroup;
    private boolean status;
    private int priority;


    public CreateTaskInputData(String taskName){

    }
}
