package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String uid;
    private final String username;
    private final String avatarPath;
    private final String password;


    public ChangePasswordInputData(String uid, String username, String avatarPath, String password) {
        this.uid = uid;
        this.username = username;
        this.avatarPath = avatarPath;
        this.password = password;
    }

    String getUid() {
        return uid;
    }

    String getUsername() {
        return username;
    }

    String getAvatarPath() {
        return avatarPath;
    }

    String getPassword() {
        return password;
    }



}
