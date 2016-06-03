package by.epam.webpharmacy.entity;

/**
 * Represents possible order's status.
 */
public enum OrderStatus {
    CART("открыт"),
    PROCESSING("в работе"),
    SHIPPING("к доставке"),
    COMPLETED("выполнен");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public String getName() {
        return name().toLowerCase();
    }
}
