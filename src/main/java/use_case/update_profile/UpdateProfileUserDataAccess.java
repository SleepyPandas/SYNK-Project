package use_case.update_profile;

import entity.User;

public interface UpdateProfileUserDataAccess {

    boolean existsByUid(String uid);

    User getByUid(String uid);

    boolean isUsernameTaken(String username);

    void save(User user);
}
