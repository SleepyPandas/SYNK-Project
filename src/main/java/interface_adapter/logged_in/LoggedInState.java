package interface_adapter.logged_in;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String uid;
    private String username = "";
    private String avatarPath = "";

    public LoggedInState(LoggedInState copy) {
        uid = copy.uid;
        username = copy.username;
        avatarPath = copy.avatarPath;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {

    }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarPath() { return avatarPath; }

    public void setAvatarPath(String avatarPath) { this.avatarPath = avatarPath; }




}
