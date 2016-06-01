package by.epam.webpharmacy.command;

import by.epam.webpharmacy.entity.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains names of all classes that implements {@see Command} interface
 */
public enum CommandName {
    ADD_ITEM(false, UserRole.MANAGER, UserRole.ADMIN),
    ADD_ITEM_TO_ORDER(false, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    DELETE_ITEM(false, UserRole.ADMIN, UserRole.MANAGER),
    BAN_USER(false, UserRole.MANAGER, UserRole.ADMIN),
    UNKNOWN(true,UserRole.GUEST, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    LOGIN(false, UserRole.GUEST),
    CHANGE_LOCALE(true, UserRole.GUEST, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER,UserRole.DOCTOR),
    REGISTER(false, UserRole.GUEST),
    LOGOUT(true, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    REMOVE_ITEM_FROM_ORDER(false,UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    SEARCH_ITEM(true, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    SUBMIT_ORDER(false,UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    VIEW_ADD_ITEM(true,  UserRole.MANAGER, UserRole.ADMIN),
    VIEW_EDIT_ITEM(true, UserRole.MANAGER, UserRole.ADMIN),
    VIEW_CATALOG(true, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    VIEW_ITEM(true, UserRole.GUEST, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    VIEW_ORDER(true, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    VIEW_PAGE(true, UserRole.GUEST, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR),
    VIEW_SHOPPING_CART(true, UserRole.USER, UserRole.ADMIN, UserRole.MANAGER, UserRole.DOCTOR);

    private boolean isGetAllowed;
    private List<UserRole> rolesAllowed = new ArrayList<>();

    CommandName(boolean isGetAllowed, UserRole... rolesAllowed) {
        this.isGetAllowed = isGetAllowed;
        this.rolesAllowed.addAll(Arrays.asList(rolesAllowed));
    }

    /**
     * Defines whether get method is allowed to request the command
     * @return true if get method is allowed
     */
    public boolean isGetAllowed() {
        return isGetAllowed;
    }

    /**
     * Defines whether a user with it's specified role is allowed to perform the command
     * @param role user's role
     * @return true if user is allowed to perform the command
     */
    public boolean isRoleAllowed(UserRole role) {
        return rolesAllowed.contains(role);
    }
}
