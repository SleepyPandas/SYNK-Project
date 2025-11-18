package use_case.update_profile;

public class UpdateProfileOutputData {
    private final String uid;
    private final String username;
    private final String avatarPath;
    private final boolean useCaseFailed;

    public UpdateProfileOutputData(String uid,
                                   String username,
                                   String avatarPath,
                                   boolean useCaseFailed) {
        this.uid = uid;
        this.username = username;
        this.avatarPath = avatarPath;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
