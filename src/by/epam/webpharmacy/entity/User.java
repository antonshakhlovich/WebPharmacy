package by.epam.webpharmacy.entity;

import java.io.Serializable;

/**
 * Represents a user of the system.
 * Each user has its unique login, along with a password, email and {@see by.epam.webpharmacy.entity.UserRole}.
 * May contain First Name, Last Name, address, phone number. Also has a boolean field, specifying whether user is banned and therefore can't
 * be authorized.
 */
public class User implements Serializable{

    private long id;
    private String email;
    private String login;
    private String salt;
    private String password;
    private String hashedPassword;
    private UserRole role;
    private boolean isBanned;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String address;

    public User() {
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public User(long id, String email, String login, String salt, UserRole role, boolean banned, String firstName,
                String lastName, String phoneNumber, String city, String address, String hashedPassword) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.salt = salt;
        this.role = role;
        this.isBanned = banned;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address = address;
        this.hashedPassword = hashedPassword;
    }

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public User setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (isBanned != user.isBanned) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (salt != null ? !salt.equals(user.salt) : user.salt != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (hashedPassword != null ? !hashedPassword.equals(user.hashedPassword) : user.hashedPassword != null)
            return false;
        if (role != user.role) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        return address != null ? address.equals(user.address) : user.address == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (hashedPassword != null ? hashedPassword.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isBanned ? 1 : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}


