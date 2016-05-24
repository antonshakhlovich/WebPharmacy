package by.epam.webpharmacy.command;

/**
 * Contains names of all classes that implemenents {@see Command} interface
 */
public enum CommandName {
    BAN_USER(false),
    UNKNOWN(true),
    LOGIN(false),
    CHANGE_LOCALE(true),
    REGISTER(false),
    LOGOUT(true),
    VIEW_ADD_ITEM(true);

    private boolean isGetAllowed;

    CommandName(boolean isGetAllowed) {
        this.isGetAllowed = isGetAllowed;
    }

    public boolean isGetAllowed() {
        return isGetAllowed;
    }
}
