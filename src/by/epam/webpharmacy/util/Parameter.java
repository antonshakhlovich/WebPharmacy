package by.epam.webpharmacy.util;

public enum Parameter {
    USER("user"),
    LOGIN_FAILED("login_failed"),
    LOGIN("login"),
    ID("id"),
    PASSWORD("password"),
    PASSWORD_MD5("password_md5"),
    ROLE("role"),
    SALT("salt"),
    BAN_STATUS("ban_status"),
    EMAIL("email"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    PHONE_NUMBER("phone_number"),
    ADDRESS("address"),
    CITY("city");


    private String name;

    Parameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
