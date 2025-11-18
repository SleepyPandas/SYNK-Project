package interface_adapter.view_tasks_and_habits;

/**
 * The state for the Login View Model.
 */
public class ViewTasksAndHabitsState {
    private String username = "";
    private String loginError;
    private String password = "";

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
