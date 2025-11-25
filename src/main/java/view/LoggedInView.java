package view;

import interface_adapter.ViewManagerModel;
import view.UpdateProfileView;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.sync_to_google_calendar.SyncToGoogleCalendarController;
import interface_adapter.sync_to_google_calendar.SyncToGoogleCalendarControllerState;
import interface_adapter.sync_to_google_calendar.SyncToGoogleCalendarViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private UpdateProfileView updateProfileView;

    private ViewManagerModel viewManagerModel;
    private SyncToGoogleCalendarController syncToGoogleCalendarController; // Injected controller to kick off calendar sync
    private SyncToGoogleCalendarViewModel syncToGoogleCalendarViewModel; //  View model providing sync result updates

    private final JLabel username;
    private final JLabel avatarLabel;

    private final JButton logOut;
    private final JButton viewLeaderboard;
    private final JButton updateProfile;
    private final JButton syncCalendarButton; //  Button to sync tasks to Google Calendar
    private final JLabel syncStatusLabel = new JLabel(); //  Inline status label for sync results



    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();
        avatarLabel = new JLabel();

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userPanel.add(avatarLabel);
        userPanel.add(usernameInfo);
        userPanel.add(username);

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        viewLeaderboard = new JButton("View Leaderboard");
        buttons.add(viewLeaderboard);

        updateProfile = new JButton("Update Profile");
        buttons.add(updateProfile);

        syncCalendarButton = new JButton("Sync to Google Calendar"); //  Create sync trigger button
        buttons.add(syncCalendarButton); // Add sync button alongside other actions

        logOut.addActionListener(this);
        
        viewLeaderboard.addActionListener(evt -> {
            if (evt.getSource().equals(viewLeaderboard) && viewManagerModel != null) {
                viewManagerModel.setState("leaderboard");
                viewManagerModel.firePropertyChanged();
            }

        });

        updateProfile.addActionListener(evt -> {
            if (evt.getSource().equals(updateProfile) && viewManagerModel != null) {
                LoggedInState state = loggedInViewModel.getState();
                String uid = state.getUid();

                if (updateProfileView != null) {
                    updateProfileView.setCurrentUid(uid);
                }

                viewManagerModel.setState("updateprofile");
                viewManagerModel.firePropertyChanged();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));





        syncCalendarButton.addActionListener(evt -> { // Invoke calendar sync when button is clicked
            if (evt.getSource().equals(syncCalendarButton) && syncToGoogleCalendarController != null) {
                final LoggedInState currentState = loggedInViewModel.getState(); // Use logged-in username as user identifier for sync
                syncToGoogleCalendarController.execute(currentState.getUsername()); // Trigger sync interactor via controller
            }
        });

        this.add(title);
        this.add(userPanel);
        this.add(buttons);
        this.add(syncStatusLabel); // Show latest sync success/error message in UI
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
            updateAvatar(state.getAvatarPath());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("sync")) { // React to sync view model updates
            final SyncToGoogleCalendarControllerState syncState = (SyncToGoogleCalendarControllerState) evt.getNewValue(); //  Capture sync state updates
            if (syncState.isSuccess()) {
                syncStatusLabel.setText(syncState.getStatusMessage()); // Reflect successful sync in label
                JOptionPane.showMessageDialog(this, syncState.getStatusMessage(), "Sync Complete", JOptionPane.INFORMATION_MESSAGE); //  Show confirmation dialog on success
            } else {
                syncStatusLabel.setText(syncState.getError()); //  Show error on failure
                JOptionPane.showMessageDialog(this, syncState.getError(), "Sync Failed", JOptionPane.ERROR_MESSAGE); //  Alert user when sync fails
            }
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setUpdateProfileView(UpdateProfileView updateProfileView) {
        this.updateProfileView = updateProfileView;
    }

    private void updateAvatar(String avatarPath) {
        if (avatarPath == null || avatarPath.isBlank()) {
            avatarLabel.setIcon(null);
            return;
        }
        ImageIcon icon = new ImageIcon(avatarPath);
        Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        avatarLabel.setIcon(new ImageIcon(img));
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void setSyncToGoogleCalendarController(SyncToGoogleCalendarController syncToGoogleCalendarController) { //  Allow builder to inject sync controller
        this.syncToGoogleCalendarController = syncToGoogleCalendarController; //  Store injected sync controller
    }

    public void setSyncToGoogleCalendarViewModel(SyncToGoogleCalendarViewModel syncToGoogleCalendarViewModel) { //  Subscribe to sync view model updates
        this.syncToGoogleCalendarViewModel = syncToGoogleCalendarViewModel; //  Capture sync view model reference
        this.syncToGoogleCalendarViewModel.addPropertyChangeListener(this); // Listen for sync result changes
    }
}
