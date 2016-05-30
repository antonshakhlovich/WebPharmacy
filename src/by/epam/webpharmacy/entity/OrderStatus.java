package by.epam.webpharmacy.entity;

/**
 * Represents possible order's status.
 */
public enum OrderStatus {
    OPEN("открыт"),
    PROCESSING("в работе"),
    DONE("к доставке"),
    CLOSED("закрыт");

    private String status;

    private OrderStatus(String status) {
        this.status = status;
    }
}
