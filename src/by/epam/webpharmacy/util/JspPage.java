package by.epam.webpharmacy.util;

import by.epam.webpharmacy.entity.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains names and paths of all possible jsp pages
 */
public enum JspPage {
    ADD_ITEM("/WEB-INF/jsp/catalog/add-item.jsp", UserRole.ADMIN, UserRole.MANAGER),
    INDEX("/index.jsp", UserRole.GUEST, UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN),
    ROOT("/", UserRole.GUEST, UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN),
    REGISTER("/register", UserRole.GUEST),
    SEARCH_ITEM("/WEB-INF/jsp/search-item.jsp", UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN),
    GUEST_HEADER("/WEB-INF/jsp/headers/guest-header.jsp", UserRole.GUEST),
    USER_HEADER("/WEB-INF/jsp/headers/user-header.jsp", UserRole.USER),
    ADMIN_HEADER("/WEB-INF/jsp/headers/admin-header.jsp", UserRole.ADMIN),
    DOCTOR_HEADER("/WEB-INF/jsp/headers/doctor-header.jsp", UserRole.DOCTOR),
    MANAGER_HEADER("/WEB-INF/jsp/headers/doctor-header.jsp", UserRole.MANAGER),
    VIEW_ITEM("/WEB-INF/jsp/catalog/view-item.jsp",UserRole.GUEST, UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN),
    VIEW_CATALOG("/WEB-INF/jsp/catalog/view-catalog.jsp",UserRole.MANAGER, UserRole.USER, UserRole.DOCTOR, UserRole.ADMIN);

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
