package interface_adapter.update_profile;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param uid the user whose password to change
     * @param username username of the user
     * @param avatarPath avatar path of the user
     * @param password the new password
     */
    public void execute(String uid, String username, String avatarPath, String password) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(
                uid, username, avatarPath, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
