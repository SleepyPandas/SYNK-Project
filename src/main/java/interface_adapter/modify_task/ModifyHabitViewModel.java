package interface_adapter.modify_task;

import interface_adapter.ViewModel;

public class ModifyHabitViewModel extends ViewModel<ModifyHabitState> {

    public ModifyHabitViewModel() {
        super("modify task");
        setState(new ModifyHabitState());
    }
}
