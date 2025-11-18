package use_case.update_profile;

import entities.User;


/**
 * DAO for the UpdateProfile Use Case.
 */
public interface UpdateProfileUserDataAccess {

    boolean existsByUid(String uid);

    User getByUid(String uid);

    boolean isUsernameTaken(String username);

    void save(User user);
}
