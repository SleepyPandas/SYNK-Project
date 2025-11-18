package use_case.update_profile;

public class UpdateProfileInputData {
    private final String uid;
    private final String newUsername;
    private final String newAvatarPath;

    public UpdateProfileInputData(String uid,
                                  String newUsername,
                                  String newAvatarPath) {
        this.uid = uid;
        this.newUsername = newUsername;
        this.newAvatarPath = newAvatarPath;
    }

    public String getUid() {
        return uid;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewAvatarPath() {
        return newAvatarPath;
    }
}

