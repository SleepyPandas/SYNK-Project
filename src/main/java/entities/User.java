package entities;
import java.util.UUID;

/**
 * A simple implementation of the User interface.
 */
public class User {

    private final String uid;
    private String username;
    private String avatarpath;
    private String password;

    public User(String uid, String username, String avatarpath, String password) {
        this.uid = uid;
        this.username = username;
        this.avatarpath = avatarpath;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarPath() {
        return avatarpath;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatarPath(String url) {
        this.avatarpath = url;
    }

    public String uuidGenerator() {
        //Generates a random UID for the users
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}


