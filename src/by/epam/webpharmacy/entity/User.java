package by.epam.webpharmacy.entity;

/**
 * Represents a user of the system.
 * Each user has its unique login, along with a password, email and {@see by.epam.webpharmacy.entity.UserRole}.
 * May contain First Name, Last Name, address, phone number. Also has a boolean field, specifying whether user is banned and therefore can't
 * be authorized.
 */
public class User {
    private long id;
    private String login;
    private String password;
    private String email;
    private UserRole role;
    private Boolean banned;

    public User() {}

    public User(long id) {
        this.id = id;
    }

    public User(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(long id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public User(long id, String login, String password, String email, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public Boolean getBanned() {
        return banned;
    }

    public User setBanned(Boolean banned) {
        this.banned = banned;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        return banned != null ? banned.equals(user.banned) : user.banned == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        return result;
    }
}
