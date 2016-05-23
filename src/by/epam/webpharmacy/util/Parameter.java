package by.epam.webpharmacy.util;

public enum Parameter {
    ADDRESS,
    BAN_STATUS,
    CITY,
    COMMAND,
    EMAIL,
    ERROR_MESSAGE,
    FIRST_NAME,
    FROM,
    ID,
    LAST_NAME,
    LOCALE,
    LOGIN,
    LOGIN_FAILED,
    PASSWORD,
    PASSWORD_MD5,
    PHONE_NUMBER,
    ROLE,
    SALT,
    SUCCESS_MESSAGE,
    USER;

    public String getName() {
        return name().toLowerCase();
    }

}
