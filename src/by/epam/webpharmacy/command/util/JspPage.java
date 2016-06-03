package by.epam.webpharmacy.command.util;

import by.epam.webpharmacy.entity.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains names and paths of all possible jsp pages
 */
public enum JspPage {
    ADD_ITEM("/WEB-INF/jsp/catalog/add-item.jsp"),
    INDEX("/index.jsp"),
    ROOT("/"),
    REGISTER("/register"),
    SEARCH_ITEM("/WEB-INF/jsp/search-item.jsp"),
    EDIT_ITEM("/WEB-INF/jsp/catalog/edit-item.jsp"),
    GUEST_HEADER("/WEB-INF/jsp/headers/guest-header.jsp"),
    USER_HEADER("/WEB-INF/jsp/headers/user-header.jsp"),
    ADMIN_HEADER("/WEB-INF/jsp/headers/admin-header.jsp"),
    DOCTOR_HEADER("/WEB-INF/jsp/headers/doctor-header.jsp"),
    MANAGER_HEADER("/WEB-INF/jsp/headers/doctor-header.jsp"),
    VIEW_ITEM("/WEB-INF/jsp/catalog/view-item.jsp"),
    VIEW_CATALOG("/WEB-INF/jsp/catalog/view-catalog.jsp"),
    VIEW_ORDER("/WEB-INF/jsp/orders/view-order.jsp"),
    VIEW_ORDERS("/WEB-INF/jsp/orders/view-orders.jsp"),
    VIEW_ALL_ORDERS("/WEB-INF/jsp/orders/view-all-orders.jsp"),
    VIEW_SHOPPING_CART("/WEB-INF/jsp/orders/view-shopping-cart.jsp");

    private String path;

    JspPage(String path) {
        this.path = path;
    }

    /**
     * @return path to jsp page
     */
    public String getPath() {
        return path;
    }

}
