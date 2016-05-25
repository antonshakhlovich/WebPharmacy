package by.epam.webpharmacy.util;

import by.epam.webpharmacy.entity.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains names and paths of all possible jsp pages
 */
public enum JspPage {
    ADD_ITEM("/WEB-INF/jsp/add-item.jsp", UserRole.ADMIN, UserRole.MANAGER),
    ROOT("/", UserRole.GUEST, UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN),
    REGISTER("/register", UserRole.GUEST),
    INDEX("/index.jsp", UserRole.GUEST, UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN);

    private String path;
    private List<UserRole> rolesAllowed = new ArrayList<>();

    JspPage(String path, UserRole... rolesAllowed) {
        this.path = path;
        this.rolesAllowed.addAll(Arrays.asList(rolesAllowed));
    }

    /**
     * @return path to jsp page
     */
    public String getPath() {
        return path;
    }

    public boolean isRoleAllowed(UserRole role) {
        return rolesAllowed.contains(role);
    }
}
