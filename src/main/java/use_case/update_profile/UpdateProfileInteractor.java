package use_case.update_profile;

import entity.User;

public class UpdateProfileInteractor implements UpdateProfileBoundary {

    private final UpdateProfileUserDataAccess userDataAccess;
    private final UpdateProfileOutputBoundary presenter;

    public UpdateProfileInteractor(UpdateProfileUserDataAccess userDataAccess,
                                   UpdateProfileOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(UpdateProfileInputData inputData) {
        String uid = inputData.getUid();
        String newUsername = inputData.getNewUsername();
        String newAvatarPath = inputData.getNewAvatarPath();

        if (!userDataAccess.existsByUid(uid)) {
            presenter.prepareFailView("User does not exist.");
            return;
        }

        User user = userDataAccess.getByUid(uid);

        if (newUsername != null && !newUsername.isBlank()) {
            String trimmed = newUsername.trim();

            if (trimmed.length() < 2 || trimmed.length() > 20) {
                presenter.prepareFailView("Username must be 2–20 characters.");
                return;
            }

            if (!trimmed.equals(user.getUsername())
                    && userDataAccess.isUsernameTaken(trimmed)) {
                presenter.prepareFailView("Username already taken.");
                return;
            }

            user.setUsername(trimmed);
        }

        if (newAvatarPath != null && !newAvatarPath.isBlank()) {
            user.setAvatarPath(newAvatarPath);
        }

        userDataAccess.save(user);

        UpdateProfileOutputData outputData = new UpdateProfileOutputData(
                user.getUid(),
                user.getUsername(),
                user.getAvatarPath(),
                false
        );

        presenter.prepareSuccessView(outputData);
    }
}
